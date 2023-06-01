import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';
import { RouteCreatorService } from './services/route-creator.service';
import { SharedModule } from './shared/shared.module';
import { UsersService } from './services/users.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SharedModule
  ],providers: [AuthService, RouteCreatorService, UsersService]
})
export class CoreModule { }
