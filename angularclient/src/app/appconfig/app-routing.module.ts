import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppFlatRentalComponent} from "../ui/app-rental/app-flat-rental.component";
import {BrowserModule} from "@angular/platform-browser";
import {JoinComponent} from "../ui/join/join.component";


const routes: Routes = [
  { path: "join", component: JoinComponent},
  { path: "app/rental", component: AppFlatRentalComponent },
  { path: '**', component: JoinComponent }
];

@NgModule({
  imports: [ BrowserModule, RouterModule.forRoot(
      routes,
      { enableTracing: true })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
