import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorUtilsService {

  constructor() { }

  extractErrorMessage(src: any, key: string): string {
    if (src[key].hasError('required')) {
      return 'You must enter value'
    } if (src[key].hasError('minlength')) {
      return 'Length of the field is small. Min length is 3 symbols.'
    } if (src[key].hasError('maxlength')) {
      return 'Value is too much. Max length is 20 symbols.'
    } if (src[key].hasError('email')) {
      return 'Email is not correct.'
    } if (src[key].hasError('pattern')) {
      return 'Password should not be empty.'
    }
  }

}
