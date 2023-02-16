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

}
