package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.Sample;
import com.ftn.sbnz.service.services.interfaces.IIdentificationService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentificationService implements IIdentificationService {

    private final KieContainer kieContainer;
    private final KieSession cepKieSession;
    private final MineralService mineralService; // Novi servis za pristup bazi

    @Autowired
    public IdentificationService(KieContainer kieContainer, MineralService mineralService) {
        this.kieContainer = kieContainer;
        this.mineralService = mineralService; // Injektujemo novi servis
        this.cepKieSession = kieContainer.newKieSession("cepKieSession");
    }

    public List<IdentificationResult> identifyMineral(Sample sample) {
        KieSession identificationSession = kieContainer.newKieSession("rulesKSession");

        List<IdentificationResult> results = new ArrayList<>();
        identificationSession.setGlobal("results", results);
        identificationSession.setGlobal("cepSession", this.cepKieSession);

        identificationSession.insert(sample);
        // Čitamo minerale iz baze umesto iz mock liste
        for (Mineral mineral : this.mineralService.findAllMinerals()) {
            identificationSession.insert(mineral);
        }

        System.out.println("Firing identification rules...");
        identificationSession.fireAllRules();
        System.out.println("Identification rules have been fired.");
        identificationSession.dispose();

        new Thread(() -> {
            System.out.println("Firing CEP rules in the background...");
            this.cepKieSession.fireAllRules();
        }).start();

        return results.stream()
                .sorted(Comparator.comparingInt(IdentificationResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    // Ovu metodu sada poziva RecursiveSearchService
    @Override
    public List<Mineral> getMineralDatabase() {
        return this.mineralService.findAllMinerals();
    }
}