package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Sample;
import com.ftn.sbnz.service.services.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/identification")
public class IdentificationController {

    private final IdentificationService identificationService;

    @Autowired
    public IdentificationController(IdentificationService identificationService) {
        this.identificationService = identificationService;
    }

    @PostMapping("/identify")
    public ResponseEntity<List<IdentificationResult>> identifyMineral(@RequestBody Sample sample) {
        List<IdentificationResult> results = identificationService.identifyMineral(sample);
        return ResponseEntity.ok(results);
    }
}

