import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
// Novi importi za modele formi
import { IdentificationSample } from '../../models/identification-sample.model';
import { HierarchyQuery } from '../../models/hierarchy-query.model';
// Importi za enume
import {IdentificationResult} from "../../models/identificaion.result";
import {Mineral} from "../../models/mineral";
import {Transparency} from "../../enums/transparency";
import {Luster} from "../../enums/luster";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  http = inject(HttpClient);
  router = inject(Router);
  authService = inject(AuthService);
  apiBaseUrl = 'http://localhost:8080/api';

  activeTab = signal<'identification' | 'hierarchy'>('identification');
  isLoading = signal(false);
  error = signal<string | null>(null);

  // Koristimo nove klase za modele formi
  idSample = new IdentificationSample();
  hierarchyQuery = new HierarchyQuery();

  // Podaci za dropdown-ove
  transparencyOptions: string[] = Object.values(Transparency);
  lusterOptions: string[] = Object.values(Luster);

  identificationResults = signal<IdentificationResult[]>([]);
  hierarchyResults = signal<Mineral[]>([]);

  submitIdentification(): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.identificationResults.set([]);

    // Šaljemo payload bez userId
    this.http.post<IdentificationResult[]>(`${this.apiBaseUrl}/identification/identify`, this.idSample)
      .subscribe({
        next: (data) => {
          this.identificationResults.set(data);
          this.isLoading.set(false);
        },
        error: (err) => {
          this.error.set('Došlo je do greške prilikom identifikacije.');
          console.error(err);
          this.isLoading.set(false);
        }
      });
  }

  submitHierarchySearch(): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.hierarchyResults.set([]);

    this.http.post<Mineral[]>(`${this.apiBaseUrl}/search/recursive`, this.hierarchyQuery)
      .subscribe({
        next: (data) => {
          this.hierarchyResults.set(data);
          this.isLoading.set(false);
        },
        error: (err) => {
          this.error.set('Došlo je do greške prilikom pretrage.');
          console.error(err);
          this.isLoading.set(false);
        }
      });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  goToNotifications(): void {
    // Morate kreirati ovu rutu i komponentu
    this.router.navigate(['/notifications']);
  }
}
