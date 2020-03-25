import { Injectable } from '@angular/core';
import {ApiService} from "../api/api.service";


const JWT_TOKEN: string = "JWT_TOKEN";


@Injectable({
  providedIn: 'root'
})
export class JWTTokenProvider {

  constructor(private api: ApiService) {}

  public authenticate() {
    this.api.getCurrentPerson().subscribe(person => {
        console.log("Spring has detected current authenticated user successfully!" + person);
      }, error => {

      }
    );
  }

  public saveJWTToken(token: string): string {
    window.sessionStorage.removeItem(JWT_TOKEN);
    window.sessionStorage.setItem(JWT_TOKEN, token);
    return window.sessionStorage.getItem(JWT_TOKEN);
  }

  public getJWTToken(): string {
    return window.sessionStorage.getItem(JWT_TOKEN);
  }

  public logout(): void {
    window.sessionStorage.removeItem(JWT_TOKEN);
  }

}
