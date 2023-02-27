import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { ChatComponent } from './components/chat/chat.component';
import { ClientiComponent } from './components/clienti/clienti.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { OrdineComponent } from './components/ordine/ordine.component';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { PagamentoComponent } from './components/pagamento/pagamento.component';
import { PasswordComponent } from './components/password/password.component';
import { ServiziComponent } from './components/servizi/servizi.component';
import { SignComponent } from './components/sign/sign.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "servizi", component: ServiziComponent},
  {path: "login", component: LoginComponent},
  {path: "sign", component: SignComponent},
  {path: "chat", component: ChatComponent, canActivate: [AuthGuard]},
  {path: "ordini", component: OrdiniComponent},
  {path: "ordini/:id", component: OrdineComponent, canActivate: [AuthGuard]},
  {path: "clienti", component: ClientiComponent},
  {path: "password", component: PasswordComponent},
  {path: "ordini/:id/pagamento", component: PagamentoComponent, canActivate: [AuthGuard]},
  {path: "", component: HomeComponent, pathMatch: 'full'},
  {path: "**", component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
