package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.enums.Luster;
import com.ftn.sbnz.model.enums.Transparency;
import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Sample;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentificationService {

    private final KieContainer kieContainer;
    private final List<Mineral> mineralDatabase;

    @Autowired
    public IdentificationService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        // Temporary Mock database
        this.mineralDatabase = new ArrayList<>();
        this.mineralDatabase.add(new Mineral(1L, "Serpentine", Arrays.asList("green", "black"), Transparency.TRANSLUCENT, Luster.GREASY, 2.5, 4.0, "white", Arrays.asList("serpentinite"), Arrays.asList("Zlatibor", "Kopaonik")));
        this.mineralDatabase.add(new Mineral(2L, "Malachite", Arrays.asList("green"), Transparency.OPAQUE, Luster.GLASSY, 3.5, 4.0, "green", Arrays.asList("limestone"), Arrays.asList("Rudnik")));
        this.mineralDatabase.add(new Mineral(3L, "Amethyst (Quartz)", Arrays.asList("purple"), Transparency.TRANSPARENT, Luster.GLASSY, 7.0, 7.0, "white", Arrays.asList("volcanic rock"), Arrays.asList("Fruška gora")));
        this.mineralDatabase.add(new Mineral(4L, "Rock Crystal (Quartz)", Arrays.asList("colorless"), Transparency.TRANSPARENT, Luster.GLASSY, 7.0, 7.0, "white", Arrays.asList("granite", "pegmatite"), Arrays.asList("Kopaonik")));
    }

    public List<IdentificationResult> identifyMineral(Sample sample) {
        KieSession kieSession = kieContainer.newKieSession();

        List<IdentificationResult> results = new ArrayList<>();
        kieSession.setGlobal("results", results);

        kieSession.insert(sample);

        for (Mineral mineral : this.mineralDatabase) {
            kieSession.insert(mineral);
        }

        System.out.println("Firing rules...");
        kieSession.fireAllRules();
        System.out.println("Rules have been fired.");

        kieSession.dispose();

        // Sorting the results in descending order by score
        return results.stream()
                .sorted(Comparator.comparingInt(IdentificationResult::getScore).reversed())
                .collect(Collectors.toList());
    }
}

