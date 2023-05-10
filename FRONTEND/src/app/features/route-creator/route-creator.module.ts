import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouteCreatorRoutingModule } from './route-creator-routing.module';
import { RouteCreatorComponent } from './route-creator.component';


@NgModule({
  declarations: [
    RouteCreatorComponent
  ],
  imports: [
    CommonModule,
    RouteCreatorRoutingModule
  ]
})
export class RouteCreatorModule { }
