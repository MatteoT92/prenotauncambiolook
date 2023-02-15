import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Servizio } from 'src/app/models/servizio';
import { OrdineApiService } from 'src/app/services/ordine-api.service';
import { ServizioApiService } from 'src/app/services/servizio-api.service';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-servizi',
  templateUrl: './servizi.component.html',
  styleUrls: ['./servizi.component.css']
})
export class ServiziComponent implements OnInit, AfterViewInit {

  isAdmin = sessionStorage.getItem('tipo');
  user: any;
  utenti!: Map<string, number>;
  servizi!: Servizio[];
  ordinaForm!: NgForm;

  constructor(private api: ServizioApiService,
              private apiUtenti: UtenteApiService,
              private apiOrdini: OrdineApiService) {

  }

  ngOnInit(): void {
   this.serviziDisponibili();
   this.utentiRegistrati();
   this.user = sessionStorage.getItem('utente');
   console.log(this.ordinaForm);
  }

  ngAfterViewInit(): void {
    console.log("AFTER");
    console.log(this.ordinaForm);
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
                              .subscribe();
  }

  onSubmit(ordinaForm: NgForm) {
    this.apiOrdini.aggiungiOrdine(ordinaForm.value.data,
                                  ordinaForm.value.orario,
                                  ordinaForm.value.quantita,
                                  ordinaForm.value.servizio,
                                  ordinaForm.value.utente)
                                  .subscribe();
  }

}
