import { Observable, Subject, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../models/interfaces/user.interface';
import { HttpClient } from '@angular/common/http';
import { PasswordUpdate } from '../models/interfaces/passwordUpdate.interface';
@Injectable({
  providedIn: 'root'
})
export class UsersService {
    private BASE_URL = 'http://localhost:8080/users';

    users: User[] = [];

    usersSubject: Subject<User[]> = new Subject<User[]>();

  constructor(private http:HttpClient) {
    this.usersSubject.next(this.users);
  }

    addUser(user: User) {
        /* this.users.push(user);
        this.usersSubject.next([...this.users]); */
        this.http.post(this.BASE_URL+'/update',user).subscribe(
            {
                next: data => {
                    console.log(data);
                },
                error: error => {
                    console.error('There was an error!', error);
                },
                complete: () => {
                    console.log('Complete!');
                }

            });

    }

    getUsers():Observable<User[]> {
        return this.http.get<User[]>(this.BASE_URL);


    }
    getUser(id: number):Observable<User> {
        return this.http.get<User>(this.BASE_URL+'/'+id);
    }

    deleteUser(user: User) {

    }

    updatePassword(updatePassword:PasswordUpdate) {
         this.http.put(this.BASE_URL+'/update/password',updatePassword,{headers: {'userId':updatePassword.idUser.toString()}}).subscribe(
            {
                next: data => {
                    console.log(data);
                },
                error: error => {
                    console.error('There was an error!', error);
                },
                complete: () => {
                    console.log('Complete!');
                }
            });
    }
}
