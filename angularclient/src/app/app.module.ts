import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './appconfig/app-routing.module';
import { AppMaterialModule} from './resourceconfig/angular-material-module';
import { AppComponent } from './app.component';

import { PersonsComponent } from './ui/persons/persons.component';
import { NavHeaderComponent } from './ui/nav-header/nav-header.component';
import { RegisterComponent } from './ui/register/register.component';
import { StartComponent } from './ui/app-rental/start.component';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    PersonsComponent,
    NavHeaderComponent,
    StartComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
