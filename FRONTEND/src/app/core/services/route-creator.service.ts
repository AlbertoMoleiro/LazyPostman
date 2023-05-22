import { Observable, BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Road } from '../models/interfaces/road.interface';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class RouteCreatorService {

    private roads: Road[] = [];

    private roadsSubject: BehaviorSubject<Road[]> = new BehaviorSubject<Road[]>(this.roads);

    constructor(private router: Router) { }

    addRoad(road: Road) {
        this.roads.push(road);
        this.roadsSubject.next([...this.roads]);
    }

    deleteRoad(road: Road) {
        const index = this.roads.indexOf(road);
        if (index > -1) {
            this.roads.splice(index, 1);
        }
        this.roadsSubject.next([...this.roads]);
    }

    getRoads(): Observable<Road[]> {
        return this.roadsSubject.asObservable();
    }

    createRoute() {
        this.roads = [];
        this.roadsSubject.next([...this.roads]);
        this.router.navigate(['/registered/route/view']);
    }
}
