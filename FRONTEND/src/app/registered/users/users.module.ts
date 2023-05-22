import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';
import { SharedModule } from 'src/app/core/shared/shared.module';
import { UsersTableComponent } from './users-management/components/users-table/users-table.component';
import {MatTableModule} from '@angular/material/table';





@NgModule({
  declarations: [
    UserSettingsComponent,
    UsersManagementComponent,
    UsersTableComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    SharedModule,
    MatTableModule
  ]
})
export class UsersModule { }
