import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HeaderComponent } from './components/header/header.component';
import { SideRightUserMenuComponent } from './components/side-right-user-menu/side-right-user-menu.component';
import { HomeComponent } from './components/home/home.component';
import { ServiziComponent } from "./components/servizi/servizi.component";
import { LoginComponent } from "./components/login/login.component";
import { SignComponent } from './components/sign/sign.component';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { ChatComponent } from './components/chat/chat.component';

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        HeaderComponent,
        SideRightUserMenuComponent,
        HomeComponent,
        LoginComponent,
        SignComponent,
        ServiziComponent,
        OrdiniComponent,
        ChatComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule
    ]
})
export class AppModule { }
