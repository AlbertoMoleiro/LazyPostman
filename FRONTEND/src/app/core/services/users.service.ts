import { Observable, Subject, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../models/interfaces/user.interface';
import { HttpClient } from '@angular/common/http';
import { PasswordUpdate } from '../models/interfaces/passwordUpdate.interface';
@Injectable({
    providedIn: 'root'
})
export class UsersService {
    private BASE_URL = 'http://localhost:8081/users';


    constructor(private http: HttpClient) {

    }

    addUser(user: User): Observable<any> {
        return this.http.post(this.BASE_URL + '/update', user);
    }

    getUsers(): Observable<User[]> {
        const userId = localStorage.getItem('userId');
        return this.http.get<User[]>(this.BASE_URL + '/all/' + userId);

    }
    getUser(id: number): Observable<User> {
        return this.http.get<User>(this.BASE_URL + '/' + id);
    }

    deleteUser(user: User) {

    }

    updatePassword(updatePassword: PasswordUpdate): Observable<any> {
        return this.http.put(this.BASE_URL + '/update/password', updatePassword, { headers: { 'userId': updatePassword.idUser.toString() } });
    }
}
