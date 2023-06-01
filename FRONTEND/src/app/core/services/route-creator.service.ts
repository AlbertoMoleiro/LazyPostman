import { Observable, BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Road } from '../models/interfaces/road.interface';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { RequestRoads } from '../models/interfaces/request-roads.interface';

@Injectable({
    providedIn: 'root'
})
export class RouteCreatorService {

    private roads: Road[] = [];

    private roadsSubject: BehaviorSubject<Road[]> = new BehaviorSubject<Road[]>(this.roads);

    private BASE_URL = 'http://localhost:8080/route';

    constructor(private router: Router,private http:HttpClient) { }

    //Table Methods

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

    //Getting from backç
    getTowns(): Observable<any> {
        return this.http.get(this.BASE_URL+'/towns');
    }
    getRoadNames(cdmuni:number): Observable<any> {
        return this.http.get(this.BASE_URL+'/roads/'+cdmuni);
    }

    createRoute() {
        const requestRoads:RequestRoads = {
            routeName: 'Ruta 1',
            roads: this.roads
        };
        this.http.post(this.BASE_URL+'/optimize',requestRoads,{ headers: { 'userId': '4' } }).subscribe(
            {
                next: data => {
                    console.log(data);
                },
                error: error => {
                    console.error('There was an error!', error);
                },
                complete: () => {
                    console.log('Complete');
                }
            }
        );
        this.roads = [];
        this.roadsSubject.next([...this.roads]);
        this.router.navigate(['/registered/route/view']);
    }
}
