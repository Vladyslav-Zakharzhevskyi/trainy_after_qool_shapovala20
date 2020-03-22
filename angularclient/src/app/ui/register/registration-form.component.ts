import {Component, OnInit} from '@angular/core';
import {Person} from "../../_models/person";
import {ApiService} from "../../api/api.service";
import {FormControl, Validators} from "@angular/forms";
import {ErrorUtilsService} from "../../util/error-utils.service";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  formValidation = {
    userName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    firstName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  };

  person = new Person();


  constructor(private apiService: ApiService, private errorUtils: ErrorUtilsService) { }

  ngOnInit(): void {
  }

  doRegistrationForPerson(): void {
    this.apiService.registerPerson(this.person).subscribe();
  }

  getErrorMessage(key: string): string {
    return this.errorUtils.extractErrorMessage(this.formValidation, key);
  }


}
