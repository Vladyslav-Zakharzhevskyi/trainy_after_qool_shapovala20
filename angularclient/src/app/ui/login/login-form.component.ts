import { Component, OnInit } from '@angular/core';
import {Person} from "../../_models/person";
import {FormControl, Validators} from "@angular/forms";
import {ErrorUtilsService} from "../../util/error-utils.service";
import {ApiService} from "../../api/api.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['../register/registration-form.component.css']
})
export class LoginFormComponent implements OnInit {

  person = new Person();

  formValidation = {
    userName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    password: new FormControl('', [Validators.required])
  };

  constructor(private errorUtilsService: ErrorUtilsService, private apiService: ApiService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.formValidation.password.invalid && this.formValidation.userName.invalid) {
      return;
    }
    this.apiService.loginPerson(this.person).subscribe(
      response => {
        console.log("user has been logged in, check spring part");
      }
    );

  }

  getErrorMessage(key: string): string {
    return this.errorUtilsService.extractErrorMessage(this.formValidation, key);
  }
}