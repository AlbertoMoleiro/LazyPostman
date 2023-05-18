import { Component } from '@angular/core';
import { MapDirectionsService } from '@angular/google-maps';
import { Router } from '@angular/router';
import { Observable, map } from 'rxjs';


@Component({
    selector: 'app-route-view',
    templateUrl: './route-view.component.html',
    styleUrls: ['./route-view.component.css']
})
export class RouteViewComponent {
    apiLoaded: Observable<boolean> = new Observable<boolean>();

    readonly directionsResults$: Observable<google.maps.DirectionsResult | undefined>;
    center: google.maps.LatLngLiteral = { lat: 24, lng: 12 };
    zoom = 6;

    waypoints: google.maps.DirectionsWaypoint[] = [
        { location: { lat: 40.47926648979756, lng: -3.85230427824624 }, stopover: true },
        { location: { lat: 40.476938110090124, lng: -3.8533896919235113 }, stopover: true },
        { location: { lat: 40.47538294425428, lng: -3.8580386572206886 }, stopover: true },
        { location: { lat: 40.478236687937795, lng: -3.8556799626623883 }, stopover: true },
        { location: { lat: 40.479896606829776, lng: -3.8600259038992633 }, stopover: true },

    ];

    constructor(private router: Router, mapDirectionsService: MapDirectionsService) {
        const request: google.maps.DirectionsRequest = {
            destination: { lat: 40.481450490571746, lng: -3.855249568664476 },
            origin: { lat: 40.481450490571746, lng: -3.855249568664476 },
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.DRIVING,
            waypoints: this.waypoints
        };
        this.directionsResults$ = mapDirectionsService.route(request).pipe(map(response => response.result));

    }
    ngOnInit(): void {


    }

    goToItinerary() {
        this.router.navigate(['/registered/route/itinerary']);
    }

}
