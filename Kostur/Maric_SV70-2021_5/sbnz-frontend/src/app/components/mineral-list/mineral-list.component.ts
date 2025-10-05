import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import {environment} from "../../env/env";
import {Mineral} from "../../models/mineral";

@Component({
  selector: 'app-mineral-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './mineral-list.component.html',
})
export class MineralListComponent implements OnInit {
  http = inject(HttpClient);
  apiBaseUrl = `${environment.apiBaseUrl}/minerals`;

  minerals = signal<Mineral[]>([]);
  isLoading = signal(true);

  ngOnInit(): void {
    this.http.get<Mineral[]>(this.apiBaseUrl).subscribe(data => {
      // Sortiramo minerale po imenu pre prikazivanja
      this.minerals.set(data.sort((a, b) => a.name.localeCompare(b.name)));
      this.isLoading.set(false);
    });
  }
}
