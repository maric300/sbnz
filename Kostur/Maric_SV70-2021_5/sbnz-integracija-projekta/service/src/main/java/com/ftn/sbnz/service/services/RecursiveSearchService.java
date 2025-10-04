package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.MineralHierarchy;
import com.ftn.sbnz.model.models.RecursiveSearchQuery;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursiveSearchService {

    private final KieContainer kieContainer;
    private final MineralService mineralService; // Novi servis za pristup bazi

    @Autowired
    public RecursiveSearchService(KieContainer kieContainer, MineralService mineralService) {
        this.kieContainer = kieContainer;
        this.mineralService = mineralService; // Injektujemo novi servis
    }

    public List<Mineral> searchByGroup(RecursiveSearchQuery query) {
        KieSession kieSession = kieContainer.newKieSession("backwardKSession");

        List<Mineral> recommendations = new ArrayList<>();
        kieSession.setGlobal("recommendations", recommendations);

        kieSession.insert(query);

        // Čitamo hijerarhiju iz baze
        for (MineralHierarchy fact : mineralService.findAllHierarchyFacts()) {
            kieSession.insert(fact);
        }

        // Čitamo minerale iz baze
        for (Mineral mineral : mineralService.findAllMinerals()) {
            kieSession.insert(mineral);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        return recommendations;
    }
}