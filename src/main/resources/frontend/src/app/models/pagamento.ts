export class Pagamento {

  id: number;
  data: Date;
  ordine: number;
  utente: number;
  importo: number;

  constructor(id: number, data: Date, ordine: number, utente: number, importo: number) {
    this.id = id;
    this.data = data;
    this.ordine = ordine;
    this.utente = utente;
    this.importo = importo;
  }

}
