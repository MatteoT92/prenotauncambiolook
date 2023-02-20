import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { OrdineApiService } from 'src/app/services/ordine-api.service';
import { ServizioApiService } from 'src/app/services/servizio-api.service';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css']
})
export class PagamentoComponent implements OnInit {

  idOrdine!: number;
  prezzario!: Map<number, number>;
  idServizio!: number;

  constructor(private api: OrdineApiService,
              private apiServizi: ServizioApiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.idOrdine = Number(sessionStorage.getItem('idOrdine'));
    this.idServizio = Number(sessionStorage.getItem('idServizio'));
    this.prezziServizi();
  }

  pagaOrdine(pagamentoForm: NgForm) {
    this.api.pagaOrdine(pagamentoForm.value.idOrdine, pagamentoForm.value.importo)
    .subscribe();
    this.router.navigate(['/ordini']);
  }

  prezziServizi() {
    this.apiServizi.serviziDisponibili()
    .subscribe(data => {
      this.prezzario = new Map<number, number>();
      data.forEach(element => {
        this.prezzario.set(element.id, element.prezzo);
      })
    })
  }

}
