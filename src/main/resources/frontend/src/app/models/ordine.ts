import { Servizio } from "./servizio";
import { Utente } from "./utente";

export class Ordine {

  id: number;
  data: Date;
  orario: Date;
  quantita: number;
  servizio: number;
  utente: number;

  constructor(id: number, data: Date, orario: Date, quantita: number, servizio: number, utente: number) {
    this.id = id;
    this.data = data;
    this.orario = orario;
    this.quantita = quantita;
    this.servizio = servizio;
    this.utente = utente;
  }

}
