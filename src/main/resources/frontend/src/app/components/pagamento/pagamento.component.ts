import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrdineApiService } from 'src/app/services/ordine-api.service';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css']
})
export class PagamentoComponent implements OnInit {

  idOrdine!: number;

  constructor(private api: OrdineApiService, private router: Router) {

  }

  ngOnInit(): void {
    this.idOrdine = Number(sessionStorage.getItem('idOrdine'));
  }

  pagaOrdine(id: number) {
    this.api.pagaOrdine(id).subscribe();
    this.router.navigate(['/ordini']);
  }

}
