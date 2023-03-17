import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Servizio } from 'src/app/models/servizio';
import { OrdineApiService } from 'src/app/services/ordine-api.service';
import { ServizioApiService } from 'src/app/services/servizio-api.service';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-servizi',
  templateUrl: './servizi.component.html',
  styleUrls: ['./servizi.component.css']
})
export class ServiziComponent implements OnInit {

  isAdmin = sessionStorage.getItem('tipo');
  user: any;
  utenti!: Map<string, number>;
  servizi!: Servizio[];
  ordinaForm!: NgForm;
  currentPage: number = 1;
  itemsPerPageAdmin: number = 8;
  totalPagesAdmin!: number;

  constructor(private api: ServizioApiService,
              private apiUtenti: UtenteApiService,
              private apiOrdini: OrdineApiService,
              private router: Router) {
  }

  ngOnInit(): void {
   this.serviziDisponibili();
   this.utentiRegistrati();
   this.user = sessionStorage.getItem('utente');
   this.totalPagesAdmin = Math.ceil(this.servizi.length / this.itemsPerPageAdmin);
  }

  serviziDisponibili(): void {
    this.api.serviziDisponibili()
      .subscribe({
        next: (data) => {
          this.servizi = data;
        },
        error: (e) => console.error(e)
      });
  }

  utentiRegistrati(): void {
    this.apiUtenti.listaUtenti()
    .subscribe({
      next: (data) => {
        this.utenti = new Map<string, number>();
        data.forEach(element => {
          this.utenti.set(element.username, element.id);
        });
      },
      error: (e) => console.error(e)
    });
  }

  onSubmitAdmin(aggiungiServizioForm: NgForm) {
    this.api.aggiungiServizio(aggiungiServizioForm.value.descrizione,
                              aggiungiServizioForm.value.prezzo)
                              .subscribe((data) => {
                                window.alert('Servizio aggiunto con successo!');
                                this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/servizi'])); // fa il refresh della tabella dei servizi offerti dopo l'aggiunta
                              });
  }

  onSubmit(ordinaForm: NgForm) {
    this.apiOrdini.aggiungiOrdine(ordinaForm.value.data,
                                  ordinaForm.value.orario,
                                  ordinaForm.value.quantita,
                                  ordinaForm.value.servizio,
                                  ordinaForm.value.utente)
                                  .subscribe((data) => {
                                    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/ordini'])); // fa il refresh della tabella dei miei ordini dopo l'aggiunta
                                  });
  }

  search(term: Event) {
    let serviziCercati: Servizio[] = [];
    let servizio = (term.target as HTMLInputElement).value;
    this.servizi.forEach(element => {
      if (element.descrizione.toLowerCase().includes(servizio.toLowerCase())) { // cerca nel servizio è presente la stringa della barra cerca
        serviziCercati.push(element); // aggiunge il servizio alla lista dei servizi cercati
      }
    });
    if (servizio.length > 0) { // se la barra cerca non è vuota
      this.servizi = serviziCercati; // aggiunge la lista dei servizi cercati alla lista dei servizi disponibili
    } else {
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/servizi']));
    }
  }

  onPrevClick() {
    this.currentPage = this.currentPage - 1;
  }

  onNextClick() {
    this.currentPage = this.currentPage + 1;
  }

}
