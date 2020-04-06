import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Advertisement} from "../../_models/advertisement";
import {ApiService} from "../../api/api.service";
import {ErrorUtilsService} from "../../util/error-utils.service";
import {FormControlUtilService, InputTypeValidation} from "../../util/form-control-util.service";
import {WithValidators} from "../../system/interfaces/with-validators";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-flat-rental',
  templateUrl: './app-flat-rental.component.html',
  styleUrls: ['./app-flat-rental.component.css']
})
export class AppFlatRentalComponent implements OnInit, WithValidators {
  public advertisementModel: Advertisement = new Advertisement();

  public personalInfoValidator: FormGroup;
  public advertisementInfoValidator: FormGroup;

  public types: string[] = [];

  constructor(private api: ApiService,
              private errorUtilsService: ErrorUtilsService,
              private formControlUtilService: FormControlUtilService,
              private translation: TranslateService) {}


  ngOnInit(): void {
    this.addValidation();
    this.initTypes();
  }

  initTypes(): void {
    let typesTrans: string[] = ['advertisement.type.sell', 'advertisement.type.rent'];
    this.types = typesTrans.map(typeTransKey => this.translation.instant(typeTransKey));
  }

  addValidation(): void {
    this.personalInfoValidator = new FormGroup({
      name: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT),
      email: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_EMAIL),
      phone: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT),
      city: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT)
    });

    this.advertisementInfoValidator = new FormGroup({
      nonEmpty: this.formControlUtilService.generate(InputTypeValidation.REQUIRED),
      title: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT),
      description: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_DESCRIPTION),
      cost: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT),
      countRoom: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT),
      floorNumber: this.formControlUtilService.generate(InputTypeValidation.DEFAULT_STRING_INPUT)
    });
  }



  submit(): void {
    if (!this.personalInfoValidator.invalid && !this.advertisementInfoValidator.invalid) {
      this.api.addAdvertisement(this.advertisementModel).subscribe();
    }
  }

  getErrorMessage(group: FormGroup, key: string): string {
    return this.errorUtilsService.extractErrorMessage(group.controls, key);
  }

}
