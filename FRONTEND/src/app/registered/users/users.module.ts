import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { UsersManagementFormComponent } from './users-management/components/users-management-form/users-management-form.component';






@NgModule({
  declarations: [
    UserSettingsComponent,
    UsersManagementComponent,
    UsersManagementFormComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    SharedModule
  ]
})
export class UsersModule { }
