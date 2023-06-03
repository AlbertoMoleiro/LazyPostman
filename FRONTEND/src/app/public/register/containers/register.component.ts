import { RouteCreatorService } from './../../../core/services/route-creator.service';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { Company } from 'src/app/core/models/interfaces/company.interface';
import { Town } from 'src/app/core/models/interfaces/town.interface';
import { CifValidator } from 'src/app/core/models/validators/Cif.validator';
import { ConfirmPassword } from 'src/app/core/models/validators/ConfirmPassword.group-validator';
import { RegisterService } from 'src/app/core/services/register.service';

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

    towns: Town[] = []

    private onDestroy$ = new Subject<void>();
    constructor(private router: Router, private formBuilder: FormBuilder, private registerService: RegisterService, private routeCreator: RouteCreatorService) {


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
                town: [
                    '',
                    [
                        Validators.required
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

    ngOnInit(): void {
        this.routeCreator.getTowns()
            .pipe(takeUntil(this.onDestroy$))
            .subscribe((towns: Town[]) => {
                this.towns = towns;
            });
    }

    goToLogin() {
        this.router.navigate(['/login']);
    }

    signUp() {

        const company: Company = {
            businessName: this.registerForm.get('businessName')?.value,
            cif: this.registerForm.get('cif')?.value,
            phoneNumber: this.registerForm.get('phoneNumber')?.value,
            email: this.registerForm.get('email')?.value,
            province: this.registerForm.get('province')?.value,
            idTown: this.registerForm.get('town')?.value,
            address: this.registerForm.get('address')?.value,
            password: this.registerForm.get('password')?.value
        }

        this.registerService.registerCompany(company);

    }
}
