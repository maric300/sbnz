import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule, KeyValuePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MineralListComponent } from '../mineral-list/mineral-list.component';
import {Mineral} from "../../models/mineral";
import {environment} from "../../env/env";

@Component({
  selector: 'app-mineral-details',
  standalone: true,
  imports: [CommonModule, RouterModule, MineralListComponent],
  templateUrl: './mineral-details.component.html',
})
export class MineralDetailsComponent implements OnInit {
  http = inject(HttpClient);
  route = inject(ActivatedRoute);
  router = inject(Router);
  apiBaseUrl = `${environment.apiBaseUrl}/minerals`;

  mineral = signal<Mineral | null>(null);
  isLoading = signal(true);
  error = signal<string | null>(null);

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const mineralId = params['id'];
      if (mineralId) {
        this.fetchMineralDetails(mineralId);
      }
    });
  }

  fetchMineralDetails(id: string): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.http.get<Mineral>(`${this.apiBaseUrl}/${id}`).subscribe({
      next: (data) => {
        this.mineral.set(data);
        this.isLoading.set(false);
      },
      error: (err) => {
        this.error.set('Mineral nije pronađen.');
        this.isLoading.set(false);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/home']);
  }
}
