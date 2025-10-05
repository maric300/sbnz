package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Sample;
import com.ftn.sbnz.model.models.auth.User;
import com.ftn.sbnz.service.repositories.IUserRepository;
import com.ftn.sbnz.service.services.interfaces.IIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal; // Importujemo Principal
import java.util.List;

@RestController
@RequestMapping("/api/identification")
public class IdentificationController {

    private final IIdentificationService identificationService;
    private final IUserRepository userRepository; // Repozitorijum za pronalaženje korisnika

    @Autowired
    public IdentificationController(IIdentificationService identificationService, IUserRepository userRepository) {
        this.identificationService = identificationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/identify")
    // KLJUČNA IZMENA: Metoda sada prihvata i 'Principal' objekat
    public ResponseEntity<List<IdentificationResult>> identifyMineral(@RequestBody Sample sample, Principal principal) {

        // 1. Dobijamo email ulogovanog korisnika iz Principal objekta
        String userEmail = principal.getName();

        // 2. Pronalazimo korisnika u bazi na osnovu emaila
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + userEmail));

        // 3. Postavljamo UUID korisnika na Sample objekat
        sample.setUserId(user.getId());

        // 4. Prosleđujemo modifikovani Sample objekat u servis
        List<IdentificationResult> results = identificationService.identifyMineral(sample);
        return ResponseEntity.ok(results);
    }
}