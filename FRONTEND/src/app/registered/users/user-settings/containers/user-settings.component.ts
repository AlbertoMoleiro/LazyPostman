import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmPassword } from 'src/app/core/models/validators/ConfirmPassword.group-validator';

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css'],
})
export class UserSettingsComponent implements OnInit {
    changePasswordForm: FormGroup;
    passwordPattern: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)\S{8,255}$/;

    nombre: string = "Juan";
    apellidos: string = "Martínez Pérez";
    email: string = "lyhxr@example.com";
    telefono: number = 645234567;

    constructor(private formBuilder: FormBuilder) {
        this.changePasswordForm = this.formBuilder.group(
            {
                currentPassword: ['', Validators.required],
                newPassword: [
                    '',
                    [
                        Validators.required,
                        Validators.minLength(8),
                        Validators.maxLength(255),
                        Validators.pattern(this.passwordPattern),
                    ],
                ],
                confirmPassword: ['', Validators.required],
            },
            {
                validators: [
                    ConfirmPassword.confirmPasswordValidator(
                        'password',
                        'confirmPassword'
                    ),
                ],
            }
        );
    }

    ngOnInit(): void {}

    onSubmit() {
        if (this.changePasswordForm.invalid) {
            return;
        }
        // Lógica para cambiar la contraseña
        const currentPassword =
            this.changePasswordForm.get('currentPassword')!.value;
        const newPassword = this.changePasswordForm.get('newPassword')!.value;

        // Realizar la llamada a la API o servicio para cambiar la contraseña
        // ...

        // Restablecer el formulario
        this.changePasswordForm.reset();
    }
}
