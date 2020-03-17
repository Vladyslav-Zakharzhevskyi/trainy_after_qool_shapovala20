import { Component, OnInit } from '@angular/core';
import { ApiPersonService } from "../../api/api.person.service";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  public persons: Object;

  constructor(private apiService: ApiPersonService) { }

  ngOnInit(): void {}

  getPersons(): void {
    this.apiService.getPersons().subscribe((data) => {
      this.persons = data;
    });
  }

}