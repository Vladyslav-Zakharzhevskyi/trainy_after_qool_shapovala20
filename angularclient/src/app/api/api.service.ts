import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Person} from "../_models/person";
import {Observable} from "rxjs";

const HOST: string = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

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

  public getCurrentPerson(): Observable<Person> {
    return this.httpClient.post<Person>(HOST + '/api/person/current', {});
  }


}
