import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
import {environment} from "../env/env";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private http = inject(HttpClient);
  private apiBaseUrl = `${environment.apiBaseUrl}/notifications`;

  unreadCount = signal(0);

  // Metoda koja osvežava broj nepročitanih notifikacija sa servera.
  fetchUnreadCount() {
    return this.http.get<number>(`${this.apiBaseUrl}/unread-count`).pipe(
      tap(count => this.unreadCount.set(count))
    );
  }
}
