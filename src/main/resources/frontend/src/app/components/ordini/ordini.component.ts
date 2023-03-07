import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ordine } from 'src/app/models/ordine';
import { OrdineApiService } from 'src/app/services/ordine-api.service';
import { ServizioApiService } from 'src/app/services/servizio-api.service';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {

  isAdmin = sessionStorage.getItem('tipo');
  ordini!: Ordine[];
  prenotazioni!: Ordine[];
  catalogo!: Map<number, string>;
  utenti!: Map<number, string>;
  itemsPerPage: number = 7;
  currentPage: number = 1;
  totalPages!: number;
  itemsPerPageAdmin: number = 10;
  totalPagesAdmin!: number;

  constructor(private api: OrdineApiService,
              private apiServizi: ServizioApiService,
              private apiUtenti: UtenteApiService,
              private router: Router) {

  }

  ngOnInit(): void {
   this.iMieiOrdini();
   this.listaPrenotazioni();
   this.serviziCatalogo();
   this.utentiRegistrati();
   this.totalPages = Math.ceil(this.ordini.length / this.itemsPerPage);
   this.totalPagesAdmin = Math.ceil(this.prenotazioni.length / this.itemsPerPage);
  }

  iMieiOrdini(): void {
    this.api.iMieiOrdini()
      .subscribe({
        next: (data) => {
          this.ordini = data;
        },
        error: (e) => console.error(e)
      });
  }

  serviziCatalogo(): void {
    this.apiServizi.serviziDisponibili()
      .subscribe({
        next: (data) => {
          this.catalogo = new Map<number, string>();
          data.forEach(element => {
            this.catalogo.set(element.id, element.descrizione);
          });
        },
        error: (e) => console.error(e)
      });
  }

  utentiRegistrati(): void {
    this.apiUtenti.listaUtenti()
    .subscribe({
      next: (data) => {
        this.utenti = new Map<number, string>();
        data.forEach(element => {
          this.utenti.set(element.id, element.username);
        });
      },
      error: (e) => console.error(e)
    });
  }

  listaPrenotazioni() {
    this.api.listaPrenotazioni()
    .subscribe({
      next: (data) => {
        this.prenotazioni = data;
      },
      error: (e) => console.error(e)
    })
  }

  rimuovi(id: number) {
    this.api.rimuoviOrdine(id)
    .subscribe((data: any) => {
      window.location.reload(); // effettua il refresh della pagina
    })
  }

  paga(idOrdine: number, idServizio: number) {
    sessionStorage.setItem('idOrdine', idOrdine.toString());
    sessionStorage.setItem('idServizio', idServizio.toString());
    this.router.navigate(['/ordini/'+idOrdine+'/pagamento']);
  }

  modifica(id: number) {
    sessionStorage.setItem('idOrdine', id.toString());
    this.router.navigate(['/ordini/'+id]);
  }

  statusButton(dataOrdine: any): boolean {
    let oggi = new Date();
    let data = new Date(dataOrdine);
    return oggi.getTime() > data.getTime();
  }

  statusButtonPaga(idOrdine: number): boolean {
    let ordine = this.ordini.find(element => element.id === idOrdine);
    return (ordine!.isPagato) ? true : false;
  }

  onPrevClick() {
    this.currentPage = this.currentPage - 1;
  }

  onNextClick() {
    this.currentPage = this.currentPage + 1;
  }

}
