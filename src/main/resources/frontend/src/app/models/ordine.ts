import { Servizio } from "./servizio";
import { Utente } from "./utente";

export class Ordine {

  id?: number;
  data?: string;
  orario?: string;
  quantita?: number;
  servizio?: Servizio;
  utente?: Utente;

}
