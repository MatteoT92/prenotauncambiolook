export class Ordine {

  id: number;
  data: Date;
  orario: Date;
  quantita: number;
  servizio: number;
  utente: number;
  isPagato: boolean;

  constructor(id: number, data: Date, orario: Date, quantita: number, servizio: number, utente: number, isPagato: boolean) {
    this.id = id;
    this.data = data;
    this.orario = orario;
    this.quantita = quantita;
    this.servizio = servizio;
    this.utente = utente;
    this.isPagato = isPagato;
  }

}
