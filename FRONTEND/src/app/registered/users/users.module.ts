import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { UsersTableComponent } from './users-management/components/users-table/users-table.component';


import { UsersManagementFormComponent } from './users-management/components/users-management-form/users-management-form.component';
import { AssignRouteComponent } from './users-management/components/assign-route/assign-route.component';


@NgModule({
  declarations: [
    UserSettingsComponent,
    UsersManagementComponent,
    UsersTableComponent,
    UsersManagementFormComponent,
    AssignRouteComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    SharedModule,
  ]
})
export class UsersModule { }
