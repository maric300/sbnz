package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.NotificationRequest;
import com.ftn.sbnz.model.models.Sample;
import com.ftn.sbnz.service.services.interfaces.IIdentificationService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentificationService implements IIdentificationService {

    private final KieContainer kieContainer;
    private final KieSession cepKieSession;
    private final MineralService mineralService;
    private final NotificationService notificationService;

    @Autowired
    public IdentificationService(KieContainer kieContainer, MineralService mineralService, NotificationService notificationService) {
        this.kieContainer = kieContainer;
        this.mineralService = mineralService;
        this.notificationService = notificationService;
        this.cepKieSession = kieContainer.newKieSession("cepKieSession");
    }

    public List<IdentificationResult> identifyMineral(Sample sample) {
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
        identificationSession.dispose();

        new Thread(() -> {
            System.out.println("Firing CEP rules in the background...");
            cepKieSession.fireAllRules();
            handleNotificationRequests();
        }).start();

        return results.stream()
                .sorted(Comparator.comparingInt(IdentificationResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    private synchronized void handleNotificationRequests() {
        // Create a new list to hold the requests to avoid concurrent modification
        List<NotificationRequest> requestsToProcess = new ArrayList<>();

        // Find all fact handles for NotificationRequest
        Collection<FactHandle> handles = cepKieSession.getFactHandles(
                object -> object instanceof NotificationRequest
        );

        // Iterate over a copy of the handles to safely retrieve and remove objects
        for (FactHandle handle : new ArrayList<>(handles)) {
            Object fact = cepKieSession.getObject(handle);
            if (fact instanceof NotificationRequest) {
                requestsToProcess.add((NotificationRequest) fact);
                cepKieSession.delete(handle); // Remove from session
            }
        }

        System.out.println("Pronađeno " + requestsToProcess.size() + " zahteva za notifikacijom.");

        // Process the collected requests
        for (NotificationRequest request : requestsToProcess) {
            notificationService.createNotificationForAllAdmins(request.getMessage());
        }
    }

    @Override
    public List<Mineral> getMineralDatabase() {
        return this.mineralService.findAllMinerals();
    }
}

