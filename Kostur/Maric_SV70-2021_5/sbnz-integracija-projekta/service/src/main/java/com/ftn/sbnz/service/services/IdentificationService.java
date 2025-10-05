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
        // --- DEO ZA TESTIRANJE ---
        // Ako je uneta specijalna komanda, pokrećemo odgovarajuću debug metodu.
        if ("load-autumn-test".equalsIgnoreCase(sample.getLocation())) {
            loadAutumnSpikeData();
            return new ArrayList<>(); // Vraćamo praznu listu, jer je ovo bio samo test
        }
        if ("load-spring-test".equalsIgnoreCase(sample.getLocation())) {
            loadSpringSpikeData();
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
        List<NotificationRequest> requestsToProcess = new ArrayList<>(handles.stream().map(h -> (NotificationRequest)cepKieSession.getObject(h)).toList());

        System.out.println("Pronađeno " + requestsToProcess.size() + " zahteva za notifikacijom.");
        for (NotificationRequest request : requestsToProcess) {
            notificationService.createNotificationForAllAdmins(request.getMessage());
            cepKieSession.delete(cepKieSession.getFactHandle(request));
        }
    }

    private synchronized void handleSeasonalTipRequests() {
        Collection<FactHandle> handles = cepKieSession.getFactHandles(object -> object instanceof CreateSeasonalTipRequest);
        if (handles.isEmpty()) return;
        List<CreateSeasonalTipRequest> requestsToProcess = new ArrayList<>(handles.stream().map(h -> (CreateSeasonalTipRequest)cepKieSession.getObject(h)).toList());

        System.out.println("Pronađeno " + requestsToProcess.size() + " zahteva za kreiranje sezonskog saveta.");
        for (CreateSeasonalTipRequest request : requestsToProcess) {
            seasonalTipService.createSeasonalTip(request.getMineralId(), request.getLocation(), request.getMessage());
            cepKieSession.delete(cepKieSession.getFactHandle(request));
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

    /**
     * TEST METODA #1: Generiše lažne podatke za JESENJI skok za Opal.
     */
    private void loadAutumnSpikeData() {
        System.out.println("--- POKRENUT TEST ZA JESENJI SKOK: Učitavam istorijske podatke za Opal... ---");

        Mineral opal = getMineralDatabase().stream()
                .filter(m -> "Opal".equals(m.getName()))
                .findFirst().orElse(null);

        if (opal == null) {
            System.out.println("TEST GREŠKA: Opal nije pronađen u bazi.");
            return;
        }

        Calendar cal = Calendar.getInstance();

        // 2024. godina
        for (int i = 0; i < 10; i++) {
            cal.set(2024, Calendar.SEPTEMBER, 15);
            cepKieSession.insert(new FindingEvent(opal.getId(), "Fruška Gora", cal.getTime()));
        }
        for (int i = 0; i < 2; i++) {
            cal.set(2024, Calendar.MAY, 1);
            cepKieSession.insert(new FindingEvent(opal.getId(), "Fruška Gora", cal.getTime()));
        }

        // 2023. godina
        for (int i = 0; i < 12; i++) {
            cal.set(2023, Calendar.OCTOBER, 5);
            cepKieSession.insert(new FindingEvent(opal.getId(), "Fruška Gora", cal.getTime()));
        }
        for (int i = 0; i < 3; i++) {
            cal.set(2023, Calendar.MARCH, 1);
            cepKieSession.insert(new FindingEvent(opal.getId(), "Fruška Gora", cal.getTime()));
        }

        System.out.println("--- Istorijski podaci za Opal su ubačeni. Čeka se okidač. ---");
    }

    /**
     * TEST METODA #2: Generiše lažne podatke za PROLEĆNI skok za Beril.
     */
    private void loadSpringSpikeData() {
        System.out.println("--- POKRENUT TEST ZA PROLEĆNI SKOK: Učitavam istorijske podatke za Beril... ---");

        Mineral beryl = getMineralDatabase().stream()
                .filter(m -> "Beril (Akvamarin)".equals(m.getName()))
                .findFirst().orElse(null);

        if (beryl == null) {
            System.out.println("TEST GREŠKA: Beril (Akvamarin) nije pronađen u bazi.");
            return;
        }

        Calendar cal = Calendar.getInstance();

        // 2024. godina
        for (int i = 0; i < 20; i++) {
            cal.set(2024, Calendar.APRIL, 10);
            cepKieSession.insert(new FindingEvent(beryl.getId(), "Cer", cal.getTime()));
        }
        for (int i = 0; i < 4; i++) {
            cal.set(2024, Calendar.AUGUST, 1);
            cepKieSession.insert(new FindingEvent(beryl.getId(), "Cer", cal.getTime()));
        }

        // 2023. godina
        for (int i = 0; i < 15; i++) {
            cal.set(2023, Calendar.MAY, 20);
            cepKieSession.insert(new FindingEvent(beryl.getId(), "Cer", cal.getTime()));
        }
        for (int i = 0; i < 3; i++) {
            cal.set(2023, Calendar.NOVEMBER, 5);
            cepKieSession.insert(new FindingEvent(beryl.getId(), "Cer", cal.getTime()));
        }

        System.out.println("--- Istorijski podaci za Beril su ubačeni. Čeka se okidač. ---");
    }

    @Override
    public List<Mineral> getMineralDatabase() {
        return this.mineralService.findAllMinerals();
    }
}