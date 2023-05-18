import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent {
    loginForm: FormGroup;


    constructor(private router: Router, private formBuilder: FormBuilder){
        this.loginForm = this.formBuilder.group({
            user: ['', Validators.required],
            password: ['', Validators.required]
          });
    }
    goToHome(){
        this.router.navigate(['/registered/home']);
    }
    goToRecover(){
        alert("Recuperar");
    }
    goToRegister(){
        this.router.navigate(['/register']);
    }
}
