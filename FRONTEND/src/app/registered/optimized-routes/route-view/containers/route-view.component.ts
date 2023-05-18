import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';


@Component({
    selector: 'app-route-view',
    templateUrl: './route-view.component.html',
    styleUrls: ['./route-view.component.css']
})
export class RouteViewComponent {
    apiLoaded: Observable<boolean>;
    constructor(httpClient: HttpClient) {
        this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyALc0EyqPY2yBRmX7ri9UQ2mrSycg7tmoY', 'callback')
            .pipe(
              map(() => true),
              catchError(() => of(false)),
            );
      }
}
