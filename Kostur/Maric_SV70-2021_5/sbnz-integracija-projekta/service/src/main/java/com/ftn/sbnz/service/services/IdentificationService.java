package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.events.FindingEvent;
import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.service.services.interfaces.IIdentificationService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IdentificationService implements IIdentificationService {

    private final KieContainer kieContainer;
    private final KieSession cepKieSession;
    private final MineralService mineralService;
    private final NotificationService notificationService;
    private final SeasonalTipService seasonalTipService;

    @Autowired
    public IdentificationService(KieContainer kieContainer, MineralService mineralService, NotificationService notificationService, SeasonalTipService seasonalTipService) {
        this.kieContainer = kieContainer;
        this.mineralService = mineralService;
        this.notificationService = notificationService;
        this.seasonalTipService = seasonalTipService;
        this.cepKieSession = kieContainer.newKieSession("cepKieSession");
    }

    public List<IdentificationResult> identifyMineral(Sample sample) {
        if ("sezonski-test".equalsIgnoreCase(sample.getLocation())) {
            triggerSeasonalPatternTest();
            return new ArrayList<>();
        }

        KieSession identificationSession = kieContainer.newKieSession("rulesKSession");

        List<IdentificationResult> results = new ArrayList<>();
        identificationSession.setGlobal("results", results);
        identificationSession.setGlobal("cepSession", this.cepKieSession);

        identificationSession.insert(sample);
        for (Mineral mineral : this.mineralService.findAllMinerals()) {
            identificationSession.insert(mineral);
        }

        System.out.println("Firing identification rules...");
        identificationSession.fireAllRules();
        System.out.println("Identification rules have been fired.");

        handleSuccessfulIdentification(identificationSession);

        identificationSession.dispose();

        new Thread(() -> {
            System.out.println("Firing CEP rules in the background...");
            this.cepKieSession.fireAllRules();
            handleNotificationRequests();
            handleSeasonalTipRequests();
        }).start();

        return results.stream()
                .sorted(Comparator.comparingInt(IdentificationResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    private synchronized void handleNotificationRequests() {
        Collection<FactHandle> handles = cepKieSession.getFactHandles(object -> object instanceof NotificationRequest);
        if (handles.isEmpty()) return;
        List<NotificationRequest> requestsToProcess = new ArrayList<>();
        for (FactHandle handle : handles) {
            requestsToProcess.add((NotificationRequest) cepKieSession.getObject(handle));
        }
        System.out.println("Pronađeno " + requestsToProcess.size() + " zahteva za notifikacijom.");
        for (NotificationRequest request : requestsToProcess) {
            notificationService.createNotificationForAllAdmins(request.getMessage());
            cepKieSession.delete(cepKieSession.getFactHandle(request));
        }
    }

    private synchronized void handleSeasonalTipRequests() {
        Collection<FactHandle> handles = cepKieSession.getFactHandles(object -> object instanceof CreateSeasonalTipRequest);
        if (handles.isEmpty()) return;

        List<CreateSeasonalTipRequest> requestsToProcess = new ArrayList<>();
        for (FactHandle handle : new ArrayList<>(handles)) {
            requestsToProcess.add((CreateSeasonalTipRequest) cepKieSession.getObject(handle));
            cepKieSession.delete(handle);
        }

        System.out.println("Pronađeno " + requestsToProcess.size() + " zahteva za kreiranje sezonskog saveta.");
        for (CreateSeasonalTipRequest request : requestsToProcess) {
            seasonalTipService.createSeasonalTip(
                    request.getMineralId(),
                    request.getLocation(),
                    request.getMessage()
            );
        }
    }

    private void handleSuccessfulIdentification(KieSession session) {
        Collection<?> topResults = session.getObjects(obj -> obj.getClass().getSimpleName().equals("TopResultFact"));
        if (!topResults.isEmpty()) {
            try {
                Object topResultFact = topResults.iterator().next();
                IdentificationResult topResult = (IdentificationResult) topResultFact.getClass().getMethod("getResult").invoke(topResultFact);
                if (topResult.isPrimaryCandidate() && topResult.getScore() > 80) {
                    Mineral identifiedMineral = topResult.getMineral();
                    if (!identifiedMineral.getLocations().isEmpty()) {
                        System.out.println("--- Uspešna identifikacija! Kreiram FindingEvent. ---");
                        String location = identifiedMineral.getLocations().get(0);
                        FindingEvent event = new FindingEvent(identifiedMineral.getId(), location);
                        this.cepKieSession.insert(event);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void triggerSeasonalPatternTest() {
        System.out.println("--- POKRENUT SEZONSKI TEST ---");

        Mineral amethyst = getMineralDatabase().stream()
                .filter(m -> "Amethyst (Quartz)".equals(m.getName()))
                .findFirst().orElse(null);

        if (amethyst == null) {
            System.out.println("TEST GREŠKA: Ametist nije pronađen u bazi.");
            return;
        }

        Calendar cal = Calendar.getInstance();

        // Generišemo 10 događaja u sezoni (sept/okt) prošle godine
        for (int i = 0; i < 10; i++) {
            cal.set(2024, Calendar.SEPTEMBER, 15);
            cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora", cal.getTime()));
        }

        // Generišemo 2 događaja van sezone prošle godine
        cal.set(2024, Calendar.MAY, 10);
        cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora", cal.getTime()));
        cal.set(2024, Calendar.JULY, 20);
        cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora", cal.getTime()));

        // Generišemo 12 događaja u sezoni (sept/okt) pretprošle godine
        for (int i = 0; i < 12; i++) {
            cal.set(2023, Calendar.OCTOBER, 5);
            cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora", cal.getTime()));
        }

        // Generišemo 3 događaja van sezone pretprošle godine
        for (int i = 0; i < 3; i++) {
            cal.set(2023, Calendar.MARCH, 1);
            cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora", cal.getTime()));
        }

        // Konačno, ubacujemo jedan događaj u "sadašnjosti" da bi se pravilo aktiviralo
        cepKieSession.insert(new FindingEvent(amethyst.getId(), "Fruška gora"));

        System.out.println("--- Lažni podaci ubačeni. Pokrećem CEP pravila. ---");
        cepKieSession.fireAllRules();
        handleSeasonalTipRequests();
    }

    @Override
    public List<Mineral> getMineralDatabase() {
        return this.mineralService.findAllMinerals();
    }
}