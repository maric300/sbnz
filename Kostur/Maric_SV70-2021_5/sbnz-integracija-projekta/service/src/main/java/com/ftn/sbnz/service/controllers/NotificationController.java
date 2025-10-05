package com.ftn.sbnz.service.controllers;

import com.ftn.sbnz.model.models.Notification;
import com.ftn.sbnz.model.models.auth.User;
import com.ftn.sbnz.service.repositories.IUserRepository;
import com.ftn.sbnz.service.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@PreAuthorize("hasAuthority('ADMIN')") // Samo admini mogu pristupiti ovom kontroleru
public class NotificationController {

    private final NotificationService notificationService;
    private final IUserRepository userRepository;

    @Autowired
    public NotificationController(NotificationService notificationService, IUserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getMyNotifications(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        // Ovu metodu treba da dodate u INotificationRepository i NotificationService
        // return ResponseEntity.ok(notificationService.findByUserId(user.getId()));
        return ResponseEntity.ok(List.of()); // Privremeno
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable UUID id) {
        // Ovu metodu treba da dodate u NotificationService
        // notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
