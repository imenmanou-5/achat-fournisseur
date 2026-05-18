import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fournisseur } from '../models/fournisseur';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root',
})
export class FournisseurService {
  private url = `${environment.apiUrl}/fournisseur`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Fournisseur[]> {
    return this.http.get<Fournisseur[]>(`${this.url}/getall`);
  }

  getById(id: number): Observable<Fournisseur> {
    return this.http.get<Fournisseur>(`${this.url}/get/${id}`);
  }

  save(f: Fournisseur): Observable<void> {
    return this.http.post<void>(`${this.url}/add`, f);
  }
  evaluer(id: number): Observable<void> {
    return this.http.put<void>(`${this.url}/evaluer/${id}`, {});
  }

  update(f: Fournisseur): Observable<void> {
    return this.http.put<void>(`${this.url}/update`, f);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }
}
