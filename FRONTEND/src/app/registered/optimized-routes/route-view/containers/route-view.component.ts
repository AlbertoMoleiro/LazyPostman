import { Component } from '@angular/core';
import { MapDirectionsService } from '@angular/google-maps';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject, map, takeUntil } from 'rxjs';
import { RouteManagementService } from 'src/app/core/services/route-management.service';


@Component({
    selector: 'app-route-view',
    templateUrl: './route-view.component.html',
    styleUrls: ['./route-view.component.css']
})
export class RouteViewComponent {
    apiLoaded: Observable<boolean> = new Observable<boolean>();

    // readonly directionsResults$: Observable<google.maps.DirectionsResult | undefined>;
    directionsResults$: Observable<google.maps.DirectionsResult | undefined> = new Observable<google.maps.DirectionsResult | undefined>();
    center: google.maps.LatLngLiteral = { lat: 24, lng: 12 };
    zoom = 6;
    origin:any =  { location: { lat: 40.47926648979756, lng: -3.85230427824624 }, stopover: true };

    waypoints: google.maps.DirectionsWaypoint[] = [
        { location: { lat: 40.47926648979756, lng: -3.85230427824624 }, stopover: true },
        { location: { lat: 40.476938110090124, lng: -3.8533896919235113 }, stopover: true },
        { location: { lat: 40.47538294425428, lng: -3.8580386572206886 }, stopover: true },
        { location: { lat: 40.478236687937795, lng: -3.8556799626623883 }, stopover: true },
        { location: { lat: 40.479896606829776, lng: -3.8600259038992633 }, stopover: true },

    ];

    idRoute:number = 0;
    onDestroy$ = new Subject<void>();
    constructor(private router: Router, private mapDirectionsService: MapDirectionsService, private activatedRoute:ActivatedRoute, private routeManagement:RouteManagementService) {

        /* Así estaba antes no tocar hasta que funcione todo */
        /* const request: google.maps.DirectionsRequest = {
            destination: { lat: 40.481450490571746, lng: -3.855249568664476 },
            origin: { lat: 40.481450490571746, lng: -3.855249568664476 },
            optimizeWaypoints: false,
            travelMode: google.maps.TravelMode.DRIVING,
            waypoints: this.waypoints
        };
        this.directionsResults$ = this.mapDirectionsService.route(request).pipe(takeUntil(this.onDestroy$),map(response => response.result)); */


    }
    ngOnInit(): void {
        this.getIdRoute();

    }


    getIdRoute() {
        this.activatedRoute.params.pipe(
            takeUntil(this.onDestroy$)
        ).subscribe(params => {
             this.idRoute = +params['id'];
             this.getWaypoints();
         });
    }
    getWaypoints() {
        this.routeManagement.getRoute(this.idRoute).pipe(
            takeUntil(this.onDestroy$),
        ).subscribe(
            (route: any) => {
                console.log(route);
                this.waypoints = route.map((waypoint: any) => { return { location: { lat: waypoint.lat, lng: waypoint.lng }, stopover: true } });
                this.calculateRoute();
            });

            /* Prueba sin routmanagement  */
         /*    const route = [    {
                "lat": 40.67325599999999,
                "lng": -4.0915057
            },{
                lat: 40.6711539,
                lng: -4.0948169
            },
            {
                lat: 40.6706376,
                lng: -4.0964994
            },
            {
                lat: 40.6708924,
                lng: -4.096188199999999
            },
            {
                lat: 40.6712372,
                lng: -4.0960919
            },
            {
                lat: 40.67058919999999,
                lng: -4.093102
            },
            {
                lat: 40.6701427,
                lng: -4.094245
            }]
            this.waypoints = route.map((waypoint: any) => { return { location: { lat: waypoint.lat, lng: waypoint.lng }, stopover: true } });
            this.calculateRoute(); */
    }
    calculateRoute() {

        this.origin =this.waypoints.shift();

        const request: google.maps.DirectionsRequest = {
            destination: this.origin.location,
            origin: this.origin.location,
            optimizeWaypoints: false,
            travelMode: google.maps.TravelMode.DRIVING,
            waypoints: this.waypoints
        };
        this.directionsResults$ = this.mapDirectionsService.route(request).pipe(takeUntil(this.onDestroy$),map(response => response.result));
    }

    goToItinerary() {
        this.router.navigate(['/registered/route/itinerary', this.idRoute]);
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }

}
