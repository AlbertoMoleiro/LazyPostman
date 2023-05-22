import { AbstractControl, ValidatorFn } from "@angular/forms"
/* bibliografia https://stackblitz.com/edit/angular-autocomplete-validation?file=src%2Fapp%2Fautocomplete-validation-example.ts */
export function autocompleteNumberValidator(validOptions: Array<number>): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (validOptions.indexOf(Number(control.value)) !== -1) {
        return null  /* valid option selected */
      }
      return { 'invalidAutocompleteString': { value: control.value } }
    }
  }