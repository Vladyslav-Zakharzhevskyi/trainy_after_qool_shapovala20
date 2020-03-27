import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppMaterialModule} from './resourceconfig/angular-material-module';
import { AppRootComponent } from './app.root.component';

import { PersonsComponent } from './ui/persons/persons.component';
import { NavHeaderComponent } from './ui/nav-header/nav-header.component';
import { RegistrationFormComponent } from './ui/register/registration-form.component';
import { LoginFormComponent } from "./ui/login/login-form.component";

import { AppFlatRentalComponent } from './ui/app-rental/app-flat-rental.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { JoinComponent } from './ui/join/join.component';
import { AuthInterceptor } from "./api/auth-interceptor/auth-interceptor.service";


@NgModule({
  declarations: [
    AppRootComponent,
    PersonsComponent,
    NavHeaderComponent,
    AppFlatRentalComponent,
    RegistrationFormComponent,
    LoginFormComponent,
    JoinComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true }],

  bootstrap: [AppRootComponent]
})
export class AppRootModule { }
