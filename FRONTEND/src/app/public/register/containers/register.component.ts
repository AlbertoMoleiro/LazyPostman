import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CifValidator } from 'src/app/core/models/validators/Cif.validator';
import { ConfirmPassword } from 'src/app/core/models/validators/ConfirmPassword.group-validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    registerForm: FormGroup;
    hide = true;

    //Regex patterns
    passwordPattern: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)\S{8,255}$/;
    phoneNumberPattern: RegExp =
        /^\+?\d{1,4}?[-.\s]?\(?\d{1,3}?\)?[-.\s]?\d{1,4}[-.\s]?\d{1,4}[-.\s]?\d{1,9}$/;
    postalCodePattern: RegExp = /^\d{5}$/;


    constructor(private router: Router, private formBuilder: FormBuilder) {
        this.registerForm = this.formBuilder.group(
            {
                businessName: ['', Validators.required],
                cif: ['', [Validators.required, CifValidator.isValid]],
                phoneNumber: [
                    '',
                    [
                        Validators.required,
                        Validators.pattern(this.phoneNumberPattern),
                    ],
                ],
                email: ['', [Validators.required, Validators.email]],
                province: ['', Validators.required],
                postCode: [
                    '',
                    [
                        Validators.required,
                        Validators.pattern(this.postalCodePattern),
                    ],
                ],
                address: ['', Validators.required],
                password: [
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

    ngOnInit() {}

    goToLogin() {
        this.router.navigateByUrl('/login');
    }

    signUp() {

    }
}
