import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RouteAssign } from '../models/interfaces/route-assign.interface';
import { Observable } from 'rxjs';
import { Itinerary } from '../models/interfaces/itinerary.interface';

@Injectable({
    providedIn: 'root'
})
export class RouteManagementService {
    private BASE_URL = 'http://localhost:8081/route-management';

    constructor(private http: HttpClient) { }

    assignRoute(idUser: number, idRoute: number) {
        const routeAssign: RouteAssign = { idRoute: idRoute, userId: idUser, };
        this.http.put(this.BASE_URL + '/assign/', routeAssign).subscribe(
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

    getRoutesUser(idUser: number): Observable<any> {
        return this.http.get(this.BASE_URL + '/user-routes/' + idUser);
    }

    getRoutesManager(idUser: number): Observable<any> {
        return this.http.get(this.BASE_URL + '/manager/routes/' + idUser);
    }
    getRoute(idRoute:number):Observable<any>{//waypoint class
        return this.http.get(this.BASE_URL + '/'+idRoute);
    }

    getItinerary(idRoute:number):Observable<any>{
        return this.http.get(this.BASE_URL + '/itinerary/'+idRoute);
    }

}
