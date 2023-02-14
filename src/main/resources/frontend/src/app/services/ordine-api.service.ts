import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ordine } from '../models/ordine';

@Injectable({
  providedIn: 'root'
})
export class OrdineApiService {

  private ordiniUrl = 'http://localhost:8081/ordini';
  private prenotazioniUrl = 'http://localhost:8081/prenotazioni';
  private rimuoviOrdineUrl = 'http://localhost:8081/rimuovi-ordine';

  constructor(private http: HttpClient) {}

  iMieiOrdini() {
    return this.http.get<Ordine[]>(this.ordiniUrl+'?username='+sessionStorage.getItem('utente'));
  }

  rimuoviOrdine(id: number) {
    return this.http.delete(this.rimuoviOrdineUrl);
  }

  listaPrenotazioni() {
    return this.http.get<Ordine[]>(this.prenotazioniUrl);
  }

}
