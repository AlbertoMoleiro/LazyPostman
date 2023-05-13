import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OptimizedRoutesRoutingModule } from './optimized-routes-routing.module';

import { RouteCreatorComponent } from './route-creator/containers/route-creator.component';
import { RouteViewComponent } from './route-view/containers/route-view.component';
import { ItineraryComponent } from './itinerary/containers/itinerary.component';



@NgModule({
  declarations: [
    RouteCreatorComponent,
    RouteViewComponent,
    ItineraryComponent
  ],
  imports: [
    CommonModule,
    OptimizedRoutesRoutingModule
  ]
})
export class OptimizedRoutesModule { }
