import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';
import { RouteCreatorService } from './services/route-creator.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],providers: [AuthService, RouteCreatorService]
})
export class CoreModule { }
