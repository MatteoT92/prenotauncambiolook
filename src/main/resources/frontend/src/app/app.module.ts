import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { ServiziComponent } from "./components/servizi/servizi.component";
import { LoginComponent } from "./components/login/login.component";
import { SignComponent } from './components/sign/sign.component';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { ChatComponent } from './components/chat/chat.component';
import { RightMenuComponent } from './components/right-menu/right-menu.component';
import { PasswordComponent } from './components/password/password.component';
import { ClientiComponent } from './components/clienti/clienti.component';
import { PagamentoComponent } from './components/pagamento/pagamento.component';
import { OrdineComponent } from './components/ordine/ordine.component';

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        HeaderComponent,
        HomeComponent,
        LoginComponent,
        SignComponent,
        ServiziComponent,
        OrdiniComponent,
        ChatComponent,
        RightMenuComponent,
        PasswordComponent,
        ClientiComponent,
        PagamentoComponent,
        OrdineComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatCardModule,
        MatButtonModule,
        MatInputModule,
        MatIconModule
    ]
})
export class AppModule { }
