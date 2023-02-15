import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Servizio } from '../models/servizio';

@Injectable({
  providedIn: 'root'
})
export class ServizioApiService {

  private serviziUrl = 'http://localhost:8081/servizi';

  constructor(private http: HttpClient) {}

  serviziDisponibili() {
    return this.http.get<Servizio[]>(this.serviziUrl);
  }

  aggiungiServizio(descrizione: string, prezzo: number) {
    return this.http.post<Servizio>(this.serviziUrl, {"descrizione": descrizione, "prezzo": prezzo});
  }

}
