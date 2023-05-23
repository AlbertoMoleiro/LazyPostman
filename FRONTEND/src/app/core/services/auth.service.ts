import { UserLogin } from './../models/interfaces/user-login.interface';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    private users:UserLogin[] =[
        {login: 'admin', password: 'admin'},
    ];

  constructor() { }

  checkLogin(): Observable<boolean> {
    let result: boolean = false;
    if(localStorage.getItem('user')){
        result = true;
    }
    return of(result);

  }

  login(user:UserLogin): boolean {
    let result: boolean = false;
    this.users.forEach((u)=>{
        if(u.login === user.login && u.password === user.password){
            localStorage.setItem('user', JSON.stringify(user));
            result = true;
        }
    });
    return result;
  }

    logout(): void {
        localStorage.removeItem('user');
    }


}
