import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { RouteCreatorService } from 'src/app/core/services/route-creator.service';

@Component({
    selector: 'app-route-creator',
    templateUrl: './route-creator.component.html',
    styleUrls: ['./route-creator.component.css']
})
export class RouteCreatorComponent {
    showTable: boolean = true;
    private onDestroy = new Subject<void>();

    constructor(private routeCreatorService: RouteCreatorService,private router:Router) {
    }

    ngOnInit(): void {
        this.routeCreatorService.getRoads().pipe(takeUntil(this.onDestroy))
            .subscribe(roads => this.showTable = roads.length > 0);
    }
    ngOnDestroy() {
        this.onDestroy.next();
        this.onDestroy.complete();
    }

    createRoute() {
        this.routeCreatorService.createRoute().subscribe(
            {
                next: data => {
                    console.log(data);
                    this.routeCreatorService.cleanRoads();
                    this.router.navigate(['/registered/home']);
                },
                error: error => {
                    alert("Error al crear ruta");
                    console.error('There was an error!', error);
                },
                complete: () => {
                    console.log('Complete');
                }
            }
        );;
    }

}
