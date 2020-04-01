import {Component, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class JoinComponent {

  public _selectedTab: number;

  selectedTab(value: number) {
    this._selectedTab = value;
  }

}
