import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Notification } from '../../models/notification.model';
import {environment} from "../../env/env";

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
  http = inject(HttpClient);
  router = inject(Router);
  apiBaseUrl = `${environment.apiBaseUrl}/notifications`;

  notifications = signal<Notification[]>([]);
  isLoading = signal(true);
  error = signal<string | null>(null);

  ngOnInit(): void {
    this.loadNotifications();
  }

  loadNotifications(): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.http.get<Notification[]>(this.apiBaseUrl).subscribe({
      next: (data) => {
        this.notifications.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.error.set('Greška pri dohvatanju notifikacija.');
        console.error(err);
        this.isLoading.set(false);
      }
    });
  }

  markAsRead(notification: Notification): void {
    if (notification.read) {
      return; // Ne šaljemo zahtev ako je već pročitana
    }
    this.http.post<Notification>(`${this.apiBaseUrl}/${notification.id}/read`, {}).subscribe({
      next: (updatedNotification) => {
        // Ažuriramo stanje na frontendu
        this.notifications.update(currentNotifications =>
          currentNotifications.map(n => n.id === updatedNotification.id ? updatedNotification : n)
        );
      },
      error: (err) => {
        console.error('Greška pri označavanju notifikacije kao pročitane', err);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/home']);
  }
}
