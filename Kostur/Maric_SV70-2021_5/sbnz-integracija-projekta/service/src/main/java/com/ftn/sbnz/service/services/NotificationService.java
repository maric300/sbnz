package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.enums.Role;
import com.ftn.sbnz.model.models.Notification;
import com.ftn.sbnz.model.models.auth.User;
import com.ftn.sbnz.service.repositories.INotificationRepository;
import com.ftn.sbnz.service.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final INotificationRepository notificationRepository;
    private final IUserRepository userRepository;

    @Autowired
    public NotificationService(INotificationRepository notificationRepository, IUserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void createNotificationForAllAdmins(String message) {
        // Pronalazimo sve administratore
        List<User> admins = userRepository.findAll().stream()
                .filter(user -> user.getRole() == Role.ADMIN)
                .toList();

        // Kreiramo notifikaciju za svakog od njih
        for (User admin : admins) {
            Notification notification = new Notification(admin, message);
            notificationRepository.save(notification);
        }
        System.out.println("--- Kreirana notifikacija za " + admins.size() + " admina: " + message);
    }
}
