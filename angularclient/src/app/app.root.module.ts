import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './config/app-routing.module';
import { AppMaterialModule} from './config/angular-material-module';
import { ApplicationEntryPointComponent } from './uicomponents/application-entry-point.component';

import { PersonsComponent } from './uicomponents/persons/persons.component';
import { RegistrationFormComponent } from './uicomponents/register/registration-form.component';
import { LoginFormComponent } from "./uicomponents/login/login-form.component";

import { AppFlatRentalComponent } from './uicomponents/app-rental/app-flat-rental.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { JoinComponent } from './uicomponents/join/join.component';
import { RequestAuthTokenInterceptor } from "./api/interceptors/request-auth-token-interceptor.service";
import { HeaderComponent } from './uicomponents/header/header.component';
import {MatStepperModule} from "@angular/material/stepper";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {TranslationModule} from "./config/translation.module";
import { AdvertismentComponent } from './uicomponents/app-rental/sections/advertisment/advertisment.component';


@NgModule({
  declarations: [
    ApplicationEntryPointComponent,
    PersonsComponent,
    AppFlatRentalComponent,
    RegistrationFormComponent,
    LoginFormComponent,
    JoinComponent,
    HeaderComponent,
    AdvertismentComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatStepperModule,
    MatCheckboxModule,
    TranslationModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: RequestAuthTokenInterceptor,
    multi: true }],

  bootstrap: [ApplicationEntryPointComponent]
})
export class AppRootModule { }
