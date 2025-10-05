package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.SeasonalTip;
import com.ftn.sbnz.service.repositories.IMineralRepository;
import com.ftn.sbnz.service.repositories.ISeasonalTipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeasonalTipService {

    private final ISeasonalTipRepository seasonalTipRepository;
    private final IMineralRepository mineralRepository;

    @Autowired
    public SeasonalTipService(ISeasonalTipRepository seasonalTipRepository, IMineralRepository mineralRepository) {
        this.seasonalTipRepository = seasonalTipRepository;
        this.mineralRepository = mineralRepository;
    }

    public void createSeasonalTip(UUID mineralId, String location, String message) {
        if (!seasonalTipRepository.existsByMineralIdAndLocation(mineralId, location)) {
            Mineral mineral = mineralRepository.findById(mineralId)
                    .orElseThrow(() -> new EntityNotFoundException("Mineral not found"));

            SeasonalTip newTip = new SeasonalTip(mineral, location, message);
            seasonalTipRepository.save(newTip);
            System.out.println("--- KREIRAN NOVI SEZONSKI SAVET U BAZI ---");
        }
    }
}