import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { Card } from 'src/app/core/models/interfaces/card.interface';
import { RequestRoute } from 'src/app/core/models/interfaces/request-route.interface';
import { RouteManagementService } from 'src/app/core/services/route-management.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent {
    cards: Card[] = []
    private onDestroy$ = new Subject<void>();
    userId: number = 0;
    constructor(private router:Router,private routeManagementService: RouteManagementService) {
        this.userId = Number(localStorage.getItem('userId'));
        this.routeManagementService.getRoutesUser(this.userId).pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (routes: RequestRoute[]) => {
                this.cards = routes.map((route: RequestRoute) => { return {idRoute:route.idRoute, title: route.name, description: "Descripción", image: '../../../assets/img/' + (Math.floor(Math.random() * 3) + 1) + '.jpg' } });
            });

    }


    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}

