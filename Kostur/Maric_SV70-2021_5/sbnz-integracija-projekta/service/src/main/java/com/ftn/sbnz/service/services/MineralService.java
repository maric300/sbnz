package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.MineralHierarchy;
import com.ftn.sbnz.service.repositories.IMineralHierarchyRepository;
import com.ftn.sbnz.service.repositories.IMineralRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MineralService {

    private final IMineralRepository mineralRepository;
    private final IMineralHierarchyRepository mineralHierarchyRepository;

    @Autowired
    public MineralService(IMineralRepository mineralRepository, IMineralHierarchyRepository mineralHierarchyRepository) {
        this.mineralRepository = mineralRepository;
        this.mineralHierarchyRepository = mineralHierarchyRepository;
    }

    public List<Mineral> findAllMinerals() {
        return mineralRepository.findAll();
    }

    public Mineral findById(UUID id) {
        return mineralRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mineral not found with id: " + id));
    }

    public List<MineralHierarchy> findAllHierarchyFacts() {
        return mineralHierarchyRepository.findAll();
    }
}