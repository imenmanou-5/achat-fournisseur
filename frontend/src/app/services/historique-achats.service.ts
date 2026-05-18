import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistoriqueAchats } from '../models/historique-achats';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HistoriqueAchatsService {
  private url = `${environment.apiUrl}/historique`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<HistoriqueAchats[]> {
    return this.http.get<HistoriqueAchats[]>(`${this.url}/getall`);
  }

  getById(id: number): Observable<HistoriqueAchats> {
    return this.http.get<HistoriqueAchats>(`${this.url}/get/${id}`);
  }

  save(h: HistoriqueAchats): Observable<void> {
    return this.http.post<void>(`${this.url}/add`, h);
  }

  update(h: HistoriqueAchats): Observable<void> {
    return this.http.put<void>(`${this.url}/update`, h);
  }
  comparerOffres(produit: string): Observable<HistoriqueAchats[]> {
    return this.http.get<HistoriqueAchats[]>(`${this.url}/comparer?produit=${produit}`);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }
}
