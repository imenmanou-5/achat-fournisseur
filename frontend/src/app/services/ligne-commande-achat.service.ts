import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LigneCommandeAchat } from '../models/ligne-commande-achat';

@Injectable({
  providedIn: 'root',
})
export class LigneCommandeAchatService {
  private url = 'http://localhost:9090/ligne';

  constructor(private http: HttpClient) {}

  getAll(): Observable<LigneCommandeAchat[]> {
    return this.http.get<LigneCommandeAchat[]>(`${this.url}/getall`);
  }

  getById(id: number): Observable<LigneCommandeAchat> {
    return this.http.get<LigneCommandeAchat>(`${this.url}/get/${id}`);
  }

  save(l: LigneCommandeAchat): Observable<void> {
    return this.http.post<void>(`${this.url}/add`, l);
  }

  update(l: LigneCommandeAchat): Observable<void> {
    return this.http.put<void>(`${this.url}/update`, l);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }
}
