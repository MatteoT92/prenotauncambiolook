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
  today = new Date();

  constructor(private api: OrdineApiService, private router: Router) {

  }

  ngOnInit(): void {
    this.idOrdine = Number(sessionStorage.getItem('idOrdine'));
  }

  onSubmit(cambiaAppuntamentoForm: NgForm) {
    if (this.isDayClosed(cambiaAppuntamentoForm.value.data)) { // verifica se la data ricade di domenica o lunedì (giorni in cui il salone è chiuso)
      window.alert('Ci dispiace, ma in questa data selezionata il salone è chiuso');
      this.today = new Date(); // reimposta la data al giorno attuale
    } else {
    let utcDate = new Date(cambiaAppuntamentoForm.value.data.getTime() - cambiaAppuntamentoForm.value.data.getTimezoneOffset() * 60000); // imposta il fuso orario su UTC
    this.api.modificaOrdine(cambiaAppuntamentoForm.value.id, utcDate, cambiaAppuntamentoForm.value.orario)
    .subscribe();
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/ordini'])); // fa il refresh della tabella degli ordini dopo la modifica
    }
  }

  isDayClosed(day: Date): boolean {
    return day.getDay() === 0 || day.getDay() === 1;
  }

}
