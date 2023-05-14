import { AbstractControl, ValidationErrors } from "@angular/forms";
/* BIBLIOGRAFÍA  https://www.tutorialesprogramacionya.com/angularya/detalleconcepto.php?punto=92&codigo=92&inicio=80*/
export class CifValidator {
    static isValid(control: AbstractControl): ValidationErrors | null {
      const cif = control.value;
      let sum = 0;
      let num = 0;
      let controlDigit = 0;
      let controlLetter = '';
      let letters = 'JABCDEFGHI';
      let letter = '';
      let regExp = /^[ABCDEFGHJKLMNPQRSUVW][-]?\d{7}[-]?[0-9JABCDEFGHI]$/;
      let valid = false;

      if (regExp.test(cif)) {
        const cleanedCif = cif.replace(/-/g, '');
        controlDigit = parseInt(cleanedCif[8]);
        for (let i = 2; i <= 6; i += 2) {
          num = parseInt(cleanedCif[i]);
          sum += num;
        }
        for (let i = 1; i <= 7; i += 2) {
          num = parseInt(cleanedCif[i]) * 2;
          if (num > 9) {
            num = Math.floor(num / 10) + (num % 10);
          }
          sum += num;
        }
        sum = 10 - (sum % 10);
        if (isNaN(controlDigit)) {
          controlLetter = cleanedCif[8];
          letter = letters[sum % 10];
          if (letter == controlLetter) {
            valid = true;
          }
        } else {
          if (sum % 10 == controlDigit) {
            valid = true;
          }
        }
      }
      if (valid) {
        return null;
      }
      return { cif: true };
    }
  }