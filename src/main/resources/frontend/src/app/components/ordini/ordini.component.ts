import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Ordine } from 'src/app/models/ordine';
import { OrdineApiService } from 'src/app/services/ordine-api.service';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {

  ordini!: Ordine[];

  constructor(private api: OrdineApiService, private router: Router) {

  }

  ngOnInit(): void {
   this.iMieiOrdini();
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
