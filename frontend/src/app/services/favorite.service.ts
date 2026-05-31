import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  private apiUrl = 'http://localhost:8080/api/favorites';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  addFavorite(propertyId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${propertyId}`, {}, { headers: this.getAuthHeaders() });
  }

  removeFavorite(propertyId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${propertyId}`, { headers: this.getAuthHeaders() });
  }

  getUserFavorites(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }

  isFavorite(propertyId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/check/${propertyId}`, { headers: this.getAuthHeaders() });
  }
}
