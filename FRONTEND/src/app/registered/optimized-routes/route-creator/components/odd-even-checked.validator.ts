import { FormGroup, ValidationErrors } from '@angular/forms';

// Esto es un validador personalizado
export function oddEvenChecked(control: FormGroup):ValidationErrors | null {
  const odd = control.get('odd');
  const even = control.get('even');

  if (odd?.value || even?.value) {
    return null;
  } else {
    return { checkboxes: 'Debes seleccionar al menos un número' };
  }
};