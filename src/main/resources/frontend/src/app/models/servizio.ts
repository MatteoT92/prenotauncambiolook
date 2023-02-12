import { Ordine } from "./ordine";

export class Servizio {

  id: number;
  descrizione: string;
  prezzo: number;

  constructor(id: number, descrizione: string, prezzo: number) {
    this.id = id;
    this.descrizione = descrizione;
    this.prezzo = prezzo;
  }

}
