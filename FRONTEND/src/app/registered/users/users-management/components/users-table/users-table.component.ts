import { Component } from '@angular/core';
import { UsersService } from 'src/app/core/services/users.service';
import { Subject, takeUntil } from 'rxjs';
import { User } from 'src/app/core/models/interfaces/user.interface';
import { MatDialog } from '@angular/material/dialog';
import { UsersManagementFormComponent } from '../users-management-form/users-management-form.component';
import { AuthService } from 'src/app/core/services/auth.service';
import { RequestRoute } from 'src/app/core/models/interfaces/request-route.interface';


@Component({
    selector: 'app-users-table',
    templateUrl: './users-table.component.html',
    styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent {
    displayedColumns: string[] = ['nombre', 'apellido1', 'apellido2', 'telefono', 'codigo_empleado', 'rol', 'buttons'];
    dataSource: User[] = [];
    isAdmin: boolean = false;
    roles: string[] = ['Administrador', 'Responsable', 'Empleado'];
    routes: RequestRoute[] = [{ idRoute: 1, name: "Ruta1" }, { idRoute: 2, name: "Ruta2" }, { idRoute: 3, name: "Ruta3" }];
    selected: RequestRoute = { idRoute: 1, name: "Ruta1" };
    private onDestroy$ = new Subject<void>();
    constructor(private userService: UsersService, public dialog: MatDialog, private authService: AuthService) {
        this.userService.getUsers().pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (users: User[]) => {
                this.dataSource = users;
                console.log(this.dataSource);
            });


    }

    ngOnInit(): void {
        this.authService.checkAdmin().pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (result) => {
                this.isAdmin = result;
            }
        )
    }
    assignRoute(id: number) {

    }

    modifyUser(id: number) {
        const dialogRef = this.dialog.open(UsersManagementFormComponent, {
            minWidth: '500px',
            data: { id: id }
        });

    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
