import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {JWTTokenProvider} from "../../service/jwt-token-provider.service";

const AUTHORIZATION: string = "Authorization";

@Injectable()
export class AuthInterceptorInterceptor implements HttpInterceptor {

  constructor(private gwtTokenProvider: JWTTokenProvider) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let jwtToken = this.gwtTokenProvider.getJWTToken();
    if (jwtToken != null) {
      request = request.clone({headers: request.headers.set(AUTHORIZATION, 'Bearer ' + jwtToken)})
    }


    return next.handle(request);
  }
}
