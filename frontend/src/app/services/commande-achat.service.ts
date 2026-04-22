import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommandeAchat } from '../models/commande-achat';

@Injectable({
  providedIn: 'root',
})
export class CommandeAchatService {
  private url = 'http://localhost:9090/commande';

  constructor(private http: HttpClient) {}

  getAll(): Observable<CommandeAchat[]> {
    return this.http.get<CommandeAchat[]>(`${this.url}/getall`);
  }

  getById(id: number): Observable<CommandeAchat> {
    return this.http.get<CommandeAchat>(`${this.url}/get/${id}`);
  }

  save(c: CommandeAchat): Observable<void> {
    return this.http.post<void>(`${this.url}/add`, c);
  }

  updateStatut(id: number, statut: string): Observable<void> {
    return this.http.put<void>(`${this.url}/statut/${id}?statut=${statut}`, {});
  }

  update(c: CommandeAchat): Observable<void> {
    return this.http.put<void>(`${this.url}/update`, c);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }
}
