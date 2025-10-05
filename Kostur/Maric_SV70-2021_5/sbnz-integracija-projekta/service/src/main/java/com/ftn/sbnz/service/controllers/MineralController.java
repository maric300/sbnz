package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.service.services.MineralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/minerals")
public class MineralController {

    private final MineralService mineralService;

    @Autowired
    public MineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    @GetMapping
    public ResponseEntity<List<Mineral>> getAllMinerals() {
        return ResponseEntity.ok(mineralService.findAllMinerals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mineral> getMineralById(@PathVariable UUID id) {
        return ResponseEntity.ok(mineralService.findById(id));
    }
}