import { Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../models/interfaces/user.interface';
@Injectable({
  providedIn: 'root'
})
export class UsersService {

    users: User[] = [
        {
            idUser: 1,
            name: 'Juan',
            lastName1: 'Perez',
            lastName2: 'Gomez',
            username: 'juanpg',
            rol: '1'
        }];

    usersSubject: Subject<User[]> = new Subject<User[]>();

  constructor() {
    this.usersSubject.next(this.users);
  }

    addUser(user: User) {
        this.users.push(user);
        this.usersSubject.next([...this.users]);
    }

    getUsers():Observable<User[]> {
        return this.usersSubject.asObservable();
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
            this.users[index].rol = rol;
        }
        this.usersSubject.next([...this.users]);
    }
}
