package com.ftn.sbnz.service.services;

import com.ftn.sbnz.model.enums.Role;
import com.ftn.sbnz.model.models.Notification;
import com.ftn.sbnz.model.models.auth.User;
import com.ftn.sbnz.service.repositories.INotificationRepository;
import com.ftn.sbnz.service.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final INotificationRepository notificationRepository;
    private final IUserRepository userRepository;

    @Autowired
    public NotificationService(INotificationRepository notificationRepository, IUserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
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

    public List<Notification> findByUserId(UUID userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Notification markAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + notificationId));

        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    public long getUnreadCount(UUID userId) {
        List<Notification> notifications = getAllNotifications();
        Long unreadNotifications = 0L;
        for (Notification notification : notifications) {
            if (!notification.isRead() && notification.getUser().getId().equals(userId)) {
                unreadNotifications++;
            }
        }
        return unreadNotifications;
    }
}
