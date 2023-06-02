import { Observable, Subject, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../models/interfaces/user.interface';
import { HttpClient } from '@angular/common/http';
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
        const index = this.users.indexOf(user);
        if (index > -1) {
            this.users.splice(index, 1);
        }
        this.usersSubject.next([...this.users]);
    }

    setUserRol(user: User, rol: string) {
        const index = this.users.indexOf(user);
        if (index > -1) {
            this.users[index].idRole = rol;
        }
        this.usersSubject.next([...this.users]);
    }
}
