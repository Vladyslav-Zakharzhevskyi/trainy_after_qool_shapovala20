import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ContextService} from "../../service/context.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private context: ContextService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.headers.has('InterceptorSkipHeader')) {
      let headers = request.headers.delete("InterceptorSkipHeader");
      return next.handle(request.clone({headers}));
    } else {

      let jwtToken = this.context.getAccessToken();
      if (jwtToken != null) {
        request = request.clone({headers: request.headers.set(this.context.getAuthenticationHeader(), jwtToken)})
      }
      return next.handle(request);
    }
  }
}
