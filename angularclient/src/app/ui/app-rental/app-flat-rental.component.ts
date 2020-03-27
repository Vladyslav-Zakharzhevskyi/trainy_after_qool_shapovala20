import { Component, OnInit } from '@angular/core';
import {ContextService} from "../../service/context.service";
import {Person} from "../../_models/person";

@Component({
  selector: 'app-flat-rental',
  templateUrl: './app-flat-rental.component.html',
  styleUrls: ['./app-flat-rental.component.css']
})
export class AppFlatRentalComponent {

  currentLoggedInUserName: string = "stub";

  constructor(context: ContextService) {
    let loggedInUser = context.getCurrentLoggedInUser();
    this.currentLoggedInUserName = loggedInUser.firstName;
  }


}
