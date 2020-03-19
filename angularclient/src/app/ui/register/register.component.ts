import { Component, OnInit } from '@angular/core';
import { Person } from "../../_models/person";
import { ApiPersonService } from "../../api/api.person.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formValidation = {
    userName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    firstName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(5), Validators.pattern("[a-zA-Z]1 [1-9]1")])
  };

  person = new Person();


  constructor(private apiService: ApiPersonService) { }

  ngOnInit(): void {
  }

  doRegistrationForPerson(): void {
    this.apiService.registerPerson(this.person).
    subscribe(data=> console.log("Server Registration has done for user: " + data));
  }

  getErrorMessage(key: string): string {
    if (this.formValidation[key].hasError('required')) {
      return 'You must enter value'
    } if (this.formValidation[key].hasError('minlength')) {
      return 'Length of the field is small. Min length is 3 symbols.'
    } if (this.formValidation[key].hasError('maxlength')) {
      return 'Value is too much. Max length is 20 symbols.'
    } if (this.formValidation[key].hasError('email')) {
      return 'Email is not correct.'
    } if (this.formValidation[key].hasError('pattern')) {
      return 'Password should contain at least 1 letter and 1 number, wih min size 5 symbols'
    }
  }

}
