import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {IdentificationResult} from "../../models/identificaion.result";
import {Mineral} from "../../models/mineral";

// Uvozimo nove interfejse iz zasebnih fajlova

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  // --- Stanje Komponente ---
  http = inject(HttpClient);
  apiBaseUrl = 'http://localhost:8080/api';

  activeTab = signal<'identification' | 'hierarchy'>('identification');
  isLoading = signal(false);
  error = signal<string | null>(null);

  // --- Modeli za forme ---
  idSample = {
    location: '',
    color: '',
    transparency: '',
    luster: '',
    rockType: null,
    userId: null
  };
  hierarchyQuery = {
    mineralGroup: '',
    location: ''
  };

  // --- Podaci za dropdown-ove ---
  transparencyOptions = ['TRANSPARENT', 'TRANSLUCENT', 'OPAQUE'];
  lusterOptions = ['GLASSY', 'GREASY', 'METALLIC', 'DULL'];

  // --- Rezultati ---
  identificationResults = signal<IdentificationResult[]>([]);
  hierarchyResults = signal<Mineral[]>([]);

  // --- Metode ---
  submitIdentification(): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.identificationResults.set([]);

    const payload = {
      ...this.idSample,
      rockType: this.idSample.rockType || null,
      userId: this.idSample.userId || null
    };

    this.http.post<IdentificationResult[]>(`${this.apiBaseUrl}/identification/identify`, payload)
      .subscribe({
        next: (data) => {
          this.identificationResults.set(data);
          this.isLoading.set(false);
        },
        error: (err) => {
          this.error.set('Došlo je do greške prilikom identifikacije. Proverite konzolu.');
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
          this.error.set('Došlo je do greške prilikom pretrage. Proverite konzolu.');
          console.error(err);
          this.isLoading.set(false);
        }
      });
  }
}

