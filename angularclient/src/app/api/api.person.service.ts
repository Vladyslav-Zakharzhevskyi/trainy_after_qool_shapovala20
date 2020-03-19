import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Person} from "../_models/person";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiPersonService {

  constructor(private httpClient: HttpClient) { }

  public getPersons(){
    return this.httpClient.get('http://localhost:8080/api/person');
  }

  public registerPerson(person: Person) : Observable<Person> {

    console.log("Called from api layer", person);
    return this.httpClient.post<Person>('http://localhost:8080/api/person', person);
  }


}
