import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ordine } from '../models/ordine';

@Injectable({
  providedIn: 'root'
})
export class OrdineApiService {

  private ordiniUrl = 'http://localhost:8081/ordini';
  private prenotazioniUrl = 'http://localhost:8081/prenotazioni';

  constructor(private http: HttpClient) {}

  iMieiOrdini() {
    return this.http.get<Ordine[]>(this.ordiniUrl+'?username='+sessionStorage.getItem('utente'));
  }

  listaPrenotazioni() {
    return this.http.get<Ordine[]>(this.prenotazioniUrl);
  }

  aggiungiOrdine(data: Date, orario: Date, quantita: number, servizio: number, utente: number) {
    return this.http.post<Ordine>(this.ordiniUrl, {"data": data, "orario": orario, "quantita": quantita, "servizio": servizio, "utente": utente});
  }

  rimuoviOrdine(id: number) {
    return this.http.delete(this.ordiniUrl+'/'+id);
  }

}
