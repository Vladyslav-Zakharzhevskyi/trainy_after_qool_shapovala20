import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './config/app-routing.module';
import { AppMaterialModule} from './config/angular-material-module';
import { ApplicationEntryPointComponent } from './uicomponent/application-entry-point.component';

import { PersonsComponent } from './uicomponent/persons/persons.component';
import { NavHeaderComponent } from './uicomponent/nav-header/nav-header.component';
import { RegistrationFormComponent } from './uicomponent/register/registration-form.component';
import { LoginFormComponent } from "./uicomponent/login/login-form.component";

import { AppFlatRentalComponent } from './uicomponent/app-rental/app-flat-rental.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { JoinComponent } from './uicomponent/join/join.component';
import { RequestAuthTokenInterceptor } from "./api/interceptors/request-auth-token-interceptor.service";


@NgModule({
  declarations: [
    ApplicationEntryPointComponent,
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
    useClass: RequestAuthTokenInterceptor,
    multi: true }],

  bootstrap: [ApplicationEntryPointComponent]
})
export class AppRootModule { }
