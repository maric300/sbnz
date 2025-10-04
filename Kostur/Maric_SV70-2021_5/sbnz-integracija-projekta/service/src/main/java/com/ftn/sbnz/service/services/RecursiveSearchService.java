package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.MineralHierarchyFact;
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
    private final IIdentificationService identificationService;

    @Autowired
    public RecursiveSearchService(KieContainer kieContainer, IIdentificationService identificationService) {
        this.kieContainer = kieContainer;
        this.identificationService = identificationService;
    }

    public List<Mineral> searchByGroup(RecursiveSearchQuery query) {
        KieSession kieSession = kieContainer.newKieSession("backwardKSession");

        List<Mineral> recommendations = new ArrayList<>();
        kieSession.setGlobal("recommendations", recommendations);

        kieSession.insert(query);

        // --- ČINJENICE o JEDNOSMERNOJ HIJERARHIJI ---
        kieSession.insert(new MineralHierarchyFact("Amethyst (Quartz)", "Kvarc"));
        kieSession.insert(new MineralHierarchyFact("Rock Crystal (Quartz)", "Kvarc"));
        kieSession.insert(new MineralHierarchyFact("Serpentine", "Silikati"));
        kieSession.insert(new MineralHierarchyFact("Kvarc", "Silikati")); // Veza u 2 koraka

        for (Mineral mineral : identificationService.getMineralDatabase()) {
            kieSession.insert(mineral);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        return recommendations;
    }
}