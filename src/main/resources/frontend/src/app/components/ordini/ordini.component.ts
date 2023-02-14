import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Ordine } from 'src/app/models/ordine';
import { OrdineApiService } from 'src/app/services/ordine-api.service';
import { ServizioApiService } from 'src/app/services/servizio-api.service';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {

  ordini!: Ordine[];
  catalogo!: Map<number, string>;

  constructor(private api: OrdineApiService, private apiServizi: ServizioApiService, private router: Router) {

  }

  ngOnInit(): void {
   this.iMieiOrdini();
   this.serviziCatalogo();
   console.log(this.ordini);
   console.log(this.catalogo);
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

/*
  onSubmit(rimuoviOrdineForm: NgForm) {
    this.api.rimuoviOrdine(rimuoviOrdineForm.value.id)
    .subscribe((data: any) => {
      console.log(data);
      this.router.navigate(['/ordini']);
    })
  }
*/
}
