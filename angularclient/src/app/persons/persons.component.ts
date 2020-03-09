import { Component, OnInit } from '@angular/core';
import { ApiService } from "../api.service";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {
  public persons: Object;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getPersons().subscribe((data) => {
      this.persons = data;
    });
  }

}