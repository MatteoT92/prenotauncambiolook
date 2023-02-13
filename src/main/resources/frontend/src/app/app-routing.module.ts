import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './components/chat/chat.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { PasswordComponent } from './components/password/password.component';
import { ServiziComponent } from './components/servizi/servizi.component';
import { SignComponent } from './components/sign/sign.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "servizi", component: ServiziComponent},
  {path: "login", component: LoginComponent},
  {path: "sign", component: SignComponent},
  {path: "chat", component: ChatComponent},
  {path: "ordini", component: OrdiniComponent},
  {path: "password", component: PasswordComponent},
  {path: "", component: HomeComponent, pathMatch: 'full'},
  {path: "**", component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
