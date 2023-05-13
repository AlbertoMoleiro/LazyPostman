import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';


@NgModule({
  declarations: [
    UserSettingsComponent,
    UsersManagementComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule
  ]
})
export class UsersModule { }
