// src/app/services/collecte.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CollecteService {
  private apiUrl = 'http://localhost:8080/api/collectes';

  constructor(private http: HttpClient) {}

  getCollectesByQuartier(quartier: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/quartier/${quartier}`);
  }
}
