import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouteViewRoutingModule } from './route-view-routing.module';
import { RouteViewComponent } from './route-view.component';


@NgModule({
  declarations: [
    RouteViewComponent
  ],
  imports: [
    CommonModule,
    RouteViewRoutingModule
  ]
})
export class RouteViewModule { }
