import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Ordine } from 'src/app/models/ordine';
import { Servizio } from 'src/app/models/servizio';
import { OrdineApiService } from 'src/app/services/ordine-api.service';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent {

  ordini: Ordine[] = [];
  servizio?: Servizio;

  constructor(private api: OrdineApiService, private router: Router) {

  }

  ngOnInit() {
   this.iMieiOrdini();
  }

  iMieiOrdini(): void {
    this.api.iMieiOrdini()
      .subscribe({
        next: (data) => {
          this.ordini = data;
          console.log(data);
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
