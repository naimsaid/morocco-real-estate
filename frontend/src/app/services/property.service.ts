import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Property, PropertyRequest, SearchRequest } from '../models/property.model';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private apiUrl = 'http://localhost:8080/api/properties';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  getAllProperties(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get(`${this.apiUrl}/public?page=${page}&size=${size}`);
  }

  getPropertyById(id: number): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/public/${id}`);
  }

  getFeaturedProperties(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get(`${this.apiUrl}/featured?page=${page}&size=${size}`);
  }

  searchProperties(request: SearchRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/public/search`, request);
  }

  createProperty(request: PropertyRequest): Observable<Property> {
    return this.http.post<Property>(this.apiUrl, request, { headers: this.getAuthHeaders() });
  }

  updateProperty(id: number, request: PropertyRequest): Observable<Property> {
    return this.http.put<Property>(`${this.apiUrl}/${id}`, request, { headers: this.getAuthHeaders() });
  }

  deleteProperty(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

  getMyProperties(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get(`${this.apiUrl}/my-properties?page=${page}&size=${size}`, { headers: this.getAuthHeaders() });
  }
}
