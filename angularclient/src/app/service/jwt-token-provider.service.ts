import {Injectable, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";


const APP_SETTINGS_KEY: string = "application_settings";

@Injectable({
  providedIn: 'root'
})
export class JWTTokenProvider {

  public JWT_TOKEN: string = "auth_token";
  public static JWT_HEADER_NAME: string = "Authentication";

  constructor(private api: ApiService) {
    // this.init();
  }


  public saveJWTToken(token: string): string {
    window.sessionStorage.removeItem(this.JWT_TOKEN);
    window.sessionStorage.setItem(this.JWT_TOKEN, token);
    return window.sessionStorage.getItem(this.JWT_TOKEN);
  }

  public getJWTToken(): string {
    return window.sessionStorage.getItem(this.JWT_TOKEN);
  }

  public logout(): void {
    window.sessionStorage.removeItem(this.JWT_TOKEN);
  }

}
