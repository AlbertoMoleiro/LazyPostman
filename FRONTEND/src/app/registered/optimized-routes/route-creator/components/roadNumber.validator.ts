import { FormGroup, ValidationErrors } from "@angular/forms";

export function roadNumberValidator(group: FormGroup): ValidationErrors | null {
    const minOdd = group.get('roadNumberMinOdd')?.value;
    const maxOdd = group.get('roadNumberMaxOdd')?.value;
    const minEven = group.get('roadNumberMinEven')?.value;
    const maxEven = group.get('roadNumberMaxEven')?.value;

    if (minOdd > maxOdd || minEven > maxEven) {
      return { invalidRoadNumbers: true };
    }

    return null;
}