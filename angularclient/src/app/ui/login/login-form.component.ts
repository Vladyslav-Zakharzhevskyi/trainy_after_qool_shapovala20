import { Component, OnInit } from '@angular/core';
import {Person} from "../../_models/person";
import {FormControl, Validators} from "@angular/forms";
import {ErrorUtilsService} from "../../util/error-utils.service";
import {ApiService} from "../../api/api.service";
import {SnackBarService} from "../../service/snack-bar.service";
import {Router} from "@angular/router";
import {JWTTokenProvider} from "../../service/jwt-token-provider.service";

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

  constructor(private errorUtilsService: ErrorUtilsService,
              private apiService: ApiService,
              protected snackBarService: SnackBarService,
              private router: Router,
              private authProvider: JWTTokenProvider) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.formValidation.password.invalid && this.formValidation.userName.invalid) {
      return;
    }
    this.apiService.loginPerson(this.person).subscribe(
      response => {
            // display its headers
        //     const keys = response.headers.keys();
        //     let headers = keys.map(key =>
        //       `${key}: ${response.headers.get(key)}`);
        // console.log(headers);

        // this.authProvider.saveJWTToken(data.accessToken);
        // this.authProvider.authenticate();
        this.snackBarService.showSnackBar("Successful login!", "success", 4000);
        // this.router.navigate(['/app/rental']);
      },
      error => {
        this.snackBarService.showSnackBar("Login has been failed!", "error", 4000);
      }
    );

  }

  getErrorMessage(key: string): string {
    return this.errorUtilsService.extractErrorMessage(this.formValidation, key);
  }
}
