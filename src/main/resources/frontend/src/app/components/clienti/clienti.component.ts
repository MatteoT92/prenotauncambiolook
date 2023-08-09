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
  currentPage: number = 1;
  itemsPerPageAdmin: number = 8;
  totalPagesAdmin!: number;

  constructor(private api: UtenteApiService) {

  }

  ngOnInit(): void {
   this.listaUtenti();
   this.totalPagesAdmin = Math.ceil(this.utenti!.length / this.itemsPerPageAdmin);
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

  onPrevClick() {
    this.currentPage = this.currentPage - 1;
  }

  onNextClick() {
    this.currentPage = this.currentPage + 1;
  }

}
