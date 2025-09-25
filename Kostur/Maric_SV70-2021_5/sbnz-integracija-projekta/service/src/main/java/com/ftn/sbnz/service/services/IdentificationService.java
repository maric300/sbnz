package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.enums.Accessibility;
import com.ftn.sbnz.model.enums.Difficulty;
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
    private final KieSession cepKieSession;

    @Autowired
    public IdentificationService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.cepKieSession = kieContainer.newKieSession("cepKieSession");
    }

    public List<IdentificationResult> identifyMineral(Sample sample) {
        KieSession identificationSession = kieContainer.newKieSession("rulesKSession");

        List<IdentificationResult> results = new ArrayList<>();
        identificationSession.setGlobal("results", results);
        identificationSession.setGlobal("cepSession", this.cepKieSession);

        identificationSession.insert(sample);
        for (Mineral mineral : this.getMineralDatabase()) {
            identificationSession.insert(mineral);
        }

        System.out.println("Firing identification rules...");
        identificationSession.fireAllRules();
        System.out.println("Identification rules have been fired.");
        identificationSession.dispose(); // Uništavamo privremenu sesiju

        new Thread(() -> {
            System.out.println("Firing CEP rules in the background...");
            this.cepKieSession.fireAllRules();
        }).start();

        return results.stream()
                .sorted(Comparator.comparingInt(IdentificationResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    // Mock baza podataka
    private List<Mineral> getMineralDatabase() {
        return Arrays.asList(
                new Mineral(1L, "Serpentine", Arrays.asList("green", "black"), Transparency.TRANSLUCENT, Luster.GREASY, 2.5, 4.0, "white", Arrays.asList("serpentinite"), Arrays.asList("Zlatibor", "Kopaonik"), Accessibility.ON_FOOT, Difficulty.MEDIUM),
                new Mineral(2L, "Malachite", Arrays.asList("green"), Transparency.OPAQUE, Luster.GLASSY, 3.5, 4.0, "green", Arrays.asList("limestone"), Arrays.asList("Rudnik"), Accessibility.BY_CAR, Difficulty.MEDIUM),
                new Mineral(3L, "Amethyst (Quartz)", Arrays.asList("purple"), Transparency.TRANSPARENT, Luster.GLASSY, 7.0, 7.0, "white", Arrays.asList("volcanic rock"), Arrays.asList("Fruška gora"), Accessibility.BY_CAR, Difficulty.HARD),
                new Mineral(4L, "Rock Crystal (Quartz)", Arrays.asList("colorless"), Transparency.TRANSPARENT, Luster.GLASSY, 7.0, 7.0, "white", Arrays.asList("granite", "pegmatite"), Arrays.asList("Kopaonik"),Accessibility.BY_CAR, Difficulty.MEDIUM)
        );
    }
}

