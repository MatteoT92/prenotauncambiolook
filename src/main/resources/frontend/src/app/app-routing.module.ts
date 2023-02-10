import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './components/chat/chat.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ServiziComponent } from './components/servizi/servizi.component';
import { SignComponent } from './components/sign/sign.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "servizi", component: ServiziComponent},
  {path: "login", component: LoginComponent},
  {path: "sign", component: SignComponent},
  {path: "logout", component: HomeComponent},
  {path: "chat", component: ChatComponent},
  {path: "", component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
