import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css']
})
export class JoinComponent {

  public _selectedTab: number;

  selectedTab(value: number) {
    this._selectedTab = value;
  }

}
