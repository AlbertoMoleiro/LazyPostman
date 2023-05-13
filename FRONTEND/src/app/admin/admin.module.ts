import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { RouteCreatorFormComponent } from './route-creator-form.component';
import { RouteCreatorComponent } from './route-creator/containers/route-creator.component';
import { RouteViewComponent } from './route-view/containers/route-view.component';
import { UserManagementComponent } from './user-management/containers/user-management.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';
import { SettingsComponent } from './settings/containers/settings.component';
import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { ItineraryComponent } from './itinerary/containers/itinerary.component';
import { AdminComponent } from './admin.component';


@NgModule({
  declarations: [
    RouteCreatorFormComponent,
    RouteCreatorComponent,
    RouteViewComponent,
    UserManagementComponent,
    UsersManagementComponent,
    SettingsComponent,
    UserSettingsComponent,
    ItineraryComponent,
    AdminComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
