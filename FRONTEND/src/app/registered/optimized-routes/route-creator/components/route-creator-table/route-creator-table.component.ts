import { Subject, takeUntil } from 'rxjs';
import { Component } from '@angular/core';
import { Road } from 'src/app/core/models/interfaces/road.interface';
import { RouteCreatorService } from 'src/app/core/services/route-creator.service';

@Component({
    selector: 'app-route-creator-table',
    templateUrl: './route-creator-table.component.html',
    styleUrls: ['./route-creator-table.component.css']
})

export class RouteCreatorTableComponent {
    displayedColumns: string[] = ['deleteButton', 'postCode', 'roadType', 'name', 'minOdd', 'maxOdd', 'minEven', 'maxEven'];
    dataSource: Road[] = [];
    private onDestroy = new Subject<void>();

    constructor(private routeCreatorService: RouteCreatorService) {
    }

    ngOnInit(): void {
        this.routeCreatorService.getRoads().pipe(takeUntil(this.onDestroy))
            .subscribe(roads => { this.dataSource = roads });
    }



    ngOnDestroy() {
        this.onDestroy.next();
        this.onDestroy.complete();
    }
    deleteRoad(road: Road) {
        this.routeCreatorService.deleteRoad(road);
    }
}
