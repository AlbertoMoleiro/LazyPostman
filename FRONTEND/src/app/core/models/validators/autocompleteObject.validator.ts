import { AbstractControl, ValidatorFn } from "@angular/forms"
/* bibliografia https://stackblitz.com/edit/angular-autocomplete-validation?file=src%2Fapp%2Fautocomplete-validation-example.ts */
export function autocompleteObjectValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
        if (typeof control.value === 'string') {
            return { 'invalidAutocompleteObject': { value: control.value } }
        }
        return null  /* valid option selected */
    }
}