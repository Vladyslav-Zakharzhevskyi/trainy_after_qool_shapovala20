import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './appconfig/app-routing.module';
import { AppComponent } from './app.component';
import { PersonsComponent } from './ui/persons/persons.component';
import { AppMaterialModule} from './resourceconfig/angular-material-module';
import { NavHeaderComponent } from './ui/nav-header/nav-header.component';
import { StartComponent } from './ui/app-rental/start.component';
import { MatMenuModule } from "@angular/material/menu";
import { MatTabsModule } from "@angular/material/tabs";

@NgModule({
  declarations: [
    AppComponent,
    PersonsComponent,
    NavHeaderComponent,
    StartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppMaterialModule,
    MatMenuModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
