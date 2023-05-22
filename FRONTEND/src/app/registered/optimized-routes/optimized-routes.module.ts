import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OptimizedRoutesRoutingModule } from './optimized-routes-routing.module';

import { RouteCreatorComponent } from './route-creator/containers/route-creator.component';
import { RouteCreatorFormComponent } from './route-creator/components/route-creator-form/route-creator-form.component';
import { RouteViewComponent } from './route-view/containers/route-view.component';
import { ItineraryComponent } from './itinerary/containers/itinerary.component';

import { GoogleMapsModule } from '@angular/google-maps';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { RouteCreatorTableComponent } from './route-creator/components/route-creator-table/route-creator-table.component';


@NgModule({
    declarations: [
        RouteCreatorComponent,
        RouteViewComponent,
        ItineraryComponent,
        RouteCreatorFormComponent,
        RouteCreatorTableComponent
    ],
    imports: [
        CommonModule,
        GoogleMapsModule,
        SharedModule,
        OptimizedRoutesRoutingModule
    ]
})
export class OptimizedRoutesModule { }
