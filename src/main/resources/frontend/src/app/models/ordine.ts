import { Servizio } from "./servizio";
import { Utente } from "./utente";

export class Ordine {

  id: number;
  data: string;
  orario: string;
  quantita: number;
  servizio: Servizio;
  utente: Utente;

  constructor(id: number, data: string, orario: string, quantita: number, servizio: Servizio, utente: Utente) {
    this.id = id;
    this.data = data;
    this.orario = orario;
    this.quantita = quantita;
    this.servizio = servizio;
    this.utente = utente;
  }

}
