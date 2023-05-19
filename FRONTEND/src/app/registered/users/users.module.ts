import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';

import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';




@NgModule({
  declarations: [
    UserSettingsComponent,
    UsersManagementComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule
  ]
})
export class UsersModule { }
