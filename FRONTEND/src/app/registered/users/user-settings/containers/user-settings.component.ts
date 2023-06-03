import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PasswordUpdate } from 'src/app/core/models/interfaces/passwordUpdate.interface';
import { ConfirmPassword } from 'src/app/core/models/validators/ConfirmPassword.group-validator';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css'],
})
export class UserSettingsComponent implements OnInit {
    changePasswordForm: FormGroup;
    passwordPattern: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)\S{8,255}$/;
    hide = true;

    id: number = 2;
    nombre: string = "Juan";
    apellidos: string = "Martínez Pérez";
    telefono: number = 645234567;

    constructor(private formBuilder: FormBuilder, private usersService:UsersService) {
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
                        'newPassword',
                        'confirmPassword'
                    ),
                ],
            }
        );
    }

    ngOnInit(): void {
        // this.id = Number(localStorage.getItem('userId'));
        this.usersService.getUser(this.id).subscribe(
            {
                next: data => {
                    this.nombre = data.name;
                    this.apellidos = data.lastname1 + " " + data.lastname2;
                    this.telefono = data.phoneNumber;
                },
                error: error => {
                    console.error('There was an error!', error);
                },
                complete: () => {
                    console.log('Complete!');
                }

            });
    }

    onSubmit() {
        if (this.changePasswordForm.invalid) {
            return;
        }
        // Lógica para cambiar la contraseña
        const currentPassword =
            this.changePasswordForm.get('currentPassword')!.value;
        const newPassword = this.changePasswordForm.get('newPassword')!.value;

        const passwordUpdate:PasswordUpdate = {idUser:this.id,password:currentPassword,newPassword:newPassword};
        // Realizar la llamada a la API o servicio para cambiar la contraseña
        this.usersService.updatePassword(passwordUpdate).subscribe(
            {
                next: data => {
                    console.log(data);
                    alert("Contraseña cambiada correctamente");
                    this.changePasswordForm.reset();
                },
                error: error => {
                    alert("Error al cambiar la contraseña");
                    console.error('There was an error!', error);
                }
            });;

    }
}
