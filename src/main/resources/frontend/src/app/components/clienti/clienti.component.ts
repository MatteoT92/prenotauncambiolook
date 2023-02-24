import { Component, OnInit } from '@angular/core';
import { Utente } from 'src/app/models/utente';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrls: ['./clienti.component.css']
})
export class ClientiComponent implements OnInit {

  utenti?: Utente[];

  constructor(private api: UtenteApiService) {

  }

  ngOnInit(): void {
   this.listaUtenti();
  }

  listaUtenti(): void {
    this.api.listaUtenti()
      .subscribe({
        next: (data) => {
          this.utenti = data;
        },
        error: (e) => console.error(e)
      });
  }

}
