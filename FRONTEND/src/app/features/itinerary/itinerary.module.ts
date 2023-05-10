import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItineraryRoutingModule } from './itinerary-routing.module';
import { ItineraryComponent } from './itinerary.component';


@NgModule({
  declarations: [
    ItineraryComponent
  ],
  imports: [
    CommonModule,
    ItineraryRoutingModule
  ]
})
export class ItineraryModule { }
