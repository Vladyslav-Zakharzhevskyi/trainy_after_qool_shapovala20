import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Person} from "../_models/person";
import {Observable} from "rxjs";
import {JWTTokenProvider} from "../service/jwt-token-provider.service";
import {ContextService} from "../service/context.service";

const HOST: string = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient, private context: ContextService) {
    this.init();
  }

  public init() {
    this.getApplicationSettings().subscribe(applicationSettings => {
      if (applicationSettings != null) {
        this.context.setApplicationSettings(applicationSettings);
      }
    });
  }

  public getApplicationSettings():Observable<any>  {
    const headers = new HttpHeaders().set('InterceptorSkipHeader', '');
    return this.httpClient.get<any>(HOST + "/api/getApplicationSettings", { headers });
  }

  public getPersons(){
    return this.httpClient.get(HOST + '/api/person');
  }

  public registerPerson(person: Person) : Observable<Person> {
    return this.httpClient.post<Person>(HOST + '/api/person/register', person);
  }

  public loginPerson(person: Person): Observable<HttpResponse<Person>>  {
    let url = HOST + '/do-login?username=' + person.username + "&password=" + person.password;
    return this.httpClient.post<Person>(url,{}, { observe: 'response' });
  }

  public getCurrentPerson(): Promise<Person> {
    const promise = new Promise<Person>((resolve, reject) => {
      const apiURL = HOST + '/api/person/current';
      this.httpClient
        .post<Person>(apiURL, {})
        .toPromise()
        .then((res: Person) => {
            // Success
            this.context.authenticate(res);
            resolve();
          },
          err => {
            // Error
            reject(err);
          }
        );
    });
    return promise;
  }


}

