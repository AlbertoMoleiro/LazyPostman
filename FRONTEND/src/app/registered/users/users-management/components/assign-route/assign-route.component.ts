import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';
import { RequestRoute } from 'src/app/core/models/interfaces/request-route.interface';
import { RouteManagementService } from 'src/app/core/services/route-management.service';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
    selector: 'app-assign-route',
    templateUrl: './assign-route.component.html',
    styleUrls: ['./assign-route.component.css']
})
export class AssignRouteComponent {
    private onDestroy$ = new Subject<void>();
    routes: RequestRoute[] = [{ idRoute: 1, name: "Ruta1" }, { idRoute: 2, name: "Ruta2" }, { idRoute: 3, name: "Ruta3" }];
    selected: RequestRoute = { idRoute: 1, name: "Ruta1" };
    idManager: number = 0;
    routesUser: RequestRoute[] = [];
    constructor(private routeManagement: RouteManagementService, public dialogRef: MatDialogRef<AssignRouteComponent>, @Inject(MAT_DIALOG_DATA) public data: { id: number }) {
        this.idManager = Number(localStorage.getItem('userId'));
    }
    ngOnInit(): void {

        this.routeManagement.getRoutesUser(this.data.id).pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (routes: RequestRoute[]) => {
                this.routesUser = routes;
            });

        this.routeManagement.getRoutesManager(this.idManager).pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (routes: RequestRoute[]) => {
                this.routes = routes;
            });
    }


    assignRoute() {

        this.routeManagement.assignRoute(this.data.id, Number(this.selected)).subscribe(
            {
                next: data => {
                    alert("Ruta asignada correctamente");
                    this.dialogRef.close();
                },
                error: error => {
                    alert("Error al asignar ruta");
                    console.error('There was an error!', error);
                }
            });;

    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
