import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './appconfig/app-routing.module';
import { AppComponent } from './app.component';
import { PersonsComponent } from './ui/persons/persons.component';
import { AppMaterialModule} from './resourceconfig/angular-material-module';
import { NavHeaderComponent } from './ui/nav-header/nav-header.component';

@NgModule({
  declarations: [
    AppComponent,
    PersonsComponent,
    NavHeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppMaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
