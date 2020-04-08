import {Component, Input} from '@angular/core';
import {HeaderConfig} from "./header-config.type";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'application-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  @Input("config") config: HeaderConfig;

  constructor(private router: ActivatedRoute) {

  }

  isTabActive(url, queryParam): boolean {
    if (!url) {
      return this.checkForParam(queryParam)
    }
    //todo Add for main case
    return false;
  }

  private checkForParam(queryParam: object): boolean {
    let param = Object.keys(queryParam);

    let navigationQParams = this.router.snapshot.root.queryParamMap;
    return param.every(
      navParam =>
        navigationQParams.get(navParam) &&
        navigationQParams.get(navParam) == queryParam[navParam]
    );
  }


}
