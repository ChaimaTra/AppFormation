import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {
  private apiUrl = 'http://api-url/statistics'; 

  constructor(private http: HttpClient) { }

  getOverallStatistics(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/overall`);
  }
}
