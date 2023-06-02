import { HttpClient } from '@angular/common/http';
import { UserLogin } from './../models/interfaces/user-login.interface';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { UserLogged } from '../models/interfaces/user-logged.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private BASE_URL: string = 'http://localhost:8080/auth';

    constructor(private http: HttpClient) { }

    checkLogin(): Observable<boolean> {
        let result: boolean = false;
        if (localStorage.getItem('userId')) {
            result = true;
        }
        return of(result);

    }
    checkRole(): Observable<boolean> {
        let result: boolean = false;
        if (Number(localStorage.getItem('idRole')) < 3 ) {
            result = true;
        }
        return of(result);
    }


    login(user: UserLogin): Observable<UserLogged> {
         return this.http.post<UserLogged>(`${this.BASE_URL}/login`, user);
    }

    logout(): void {
        localStorage.removeItem('userId');
        localStorage.removeItem('idRole');
    }


}
