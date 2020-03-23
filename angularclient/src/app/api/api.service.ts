import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Person} from "../_models/person";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  public getPersons(){
    return this.httpClient.get('http://localhost:8080/api/person');
  }

  public registerPerson(person: Person) : Observable<Person> {
    return this.httpClient.post<Person>('http://localhost:8080/api/person/register', person);
  }

  public loginPerson(person: Person): Observable<Person> {
    let url = "http://localhost:8080/login?username="+person.username+"&password="+person.password;
    return this.httpClient.post<Person>(url, {});
  }


}
