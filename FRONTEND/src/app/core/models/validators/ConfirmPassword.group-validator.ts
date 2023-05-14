import { FormGroup, AbstractControl, ValidationErrors } from '@angular/forms';

export class ConfirmPassword {
  static confirmPasswordValidator(
    controlName: string,
    matchingControlName: string
  ): (control: AbstractControl) => ValidationErrors | null {
    return (control: AbstractControl): ValidationErrors | null => {
      const formGroup = control as FormGroup;
      const originalControl = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl?.errors && !matchingControl?.errors['mustMatch']) {
        return null;
      }

      if (originalControl?.value !== matchingControl?.value) {
        matchingControl?.setErrors({ mustMatch: true });
        return { mustMatch: true };
      } else {
        matchingControl?.setErrors(null);
        return null;
      }
    };
  }
}