import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { OrdineApiService } from 'src/app/services/ordine-api.service';

@Component({
  selector: 'app-ordine',
  templateUrl: './ordine.component.html',
  styleUrls: ['./ordine.component.css']
})
export class OrdineComponent implements OnInit {

  idOrdine!: number;

  constructor(private api: OrdineApiService, private router: Router) {

  }

  ngOnInit(): void {
    this.idOrdine = Number(sessionStorage.getItem('idOrdine'));
  }

  onSubmit(cambiaAppuntamentoForm: NgForm) {
    this.api.modificaOrdine(cambiaAppuntamentoForm.value.id, cambiaAppuntamentoForm.value.data, cambiaAppuntamentoForm.value.orario)
    .subscribe();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/ordini'])); // fa il refresh della tabella degli ordini dopo la modifica
  }

}
