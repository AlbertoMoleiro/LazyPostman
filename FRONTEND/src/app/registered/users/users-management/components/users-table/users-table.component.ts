import { Component } from '@angular/core';
import { UsersService } from 'src/app/core/services/users.service';
import { Subject, takeUntil } from 'rxjs';
import { User } from 'src/app/core/models/interfaces/user.interface';
import { MatDialog } from '@angular/material/dialog';
import { UsersManagementFormComponent } from '../users-management-form/users-management-form.component';


@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent {
  displayedColumns: string[] = ['nombre', 'apellido1', 'apellido2','telefono','codigo_empleado','rol','asignar','promociona'];
  dataSource:User[] = [];

  private onDestroy$ = new Subject<void>();
  constructor(private userService:UsersService,public dialog: MatDialog){
    this.userService.getUsers().pipe(
        takeUntil(this.onDestroy$),
         ).subscribe(
        (users:User[]) => {
            this.dataSource = users;
            console.log(this.dataSource);
        });


  }

  ngOnInit():void{

  }
  assignRoute(id: number) {

  }

  modifyUser(id: number) {
    const dialogRef = this.dialog.open(UsersManagementFormComponent,{
        minWidth: '500px',
        data: {id: id}
    });

  }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
