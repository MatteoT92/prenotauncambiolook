import { Inject } from '@angular/core';
import { Component, OnInit} from '@angular/core';
import { Servizio } from 'src/app/models/servizio';
import { ServizioApiService } from 'src/app/services/servizio-api.service';

@Component({
  selector: 'app-servizi',
  templateUrl: './servizi.component.html',
  styleUrls: ['./servizi.component.css']
})
export class ServiziComponent implements OnInit {

  servizi?: Servizio[];

  constructor(private api: ServizioApiService) {

  }

  ngOnInit() {

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
