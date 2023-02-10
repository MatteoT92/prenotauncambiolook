import { Inject } from '@angular/core';
import { Component, OnInit} from '@angular/core';
import { Servizio } from 'src/app/models/servizio';
import { ServizioApiService } from 'src/app/services/servizio-api.service';

@Component({
  selector: 'app-servizi',
  templateUrl: './servizi.component.html',
  styleUrls: ['./servizi.component.css'],
  template: `
    {{ servizi }}
  `
})
export class ServiziComponent implements OnInit {

  servizi: any;

  constructor(private api: ServizioApiService) {

  }

  ngOnInit() {
    /*
    this.api.serviziDisponibili().subscribe((servizi: any) => {
      this.servizi = Object.keys(servizi).map((key) => {return servizi[key]});
    });
    */
   this.serviziDisponibili();
  }

  serviziDisponibili(): void {
    this.api.serviziDisponibili()
      .subscribe({
        next: (data) => {
          this.servizi = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

}
