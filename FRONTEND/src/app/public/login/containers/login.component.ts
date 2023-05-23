import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserLogin } from 'src/app/core/models/interfaces/user-login.interface';
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent {
    loginForm: FormGroup;


    constructor(private router: Router, private formBuilder: FormBuilder, private authService:AuthService){
        this.loginForm = this.formBuilder.group({
            user: ['', Validators.required],
            password: ['', Validators.required]
          });
    }
    login(){
        const user:UserLogin = {
            login: this.loginForm.value.user,
            password: this.loginForm.value.password
        }
        if(this.authService.login(user)){
            this.router.navigate(['/registered']);
        }else{
            alert("Usuario o contraseña incorrectos");
        }

    }
    goToRecover(){
        alert("Recuperar");
    }
    goToRegister(){
        this.router.navigate(['/register']);
    }
}
