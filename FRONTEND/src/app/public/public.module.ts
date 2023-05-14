import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { PublicRoutingModule } from './public-routing.module';
import { SharedModule } from '../core/shared/shared.module';

import { PublicComponent } from './public.component';
import { LoginComponent } from './login/containers/login.component';
import { LandingComponent } from './landing/containers/landing.component';
import { RegisterComponent } from './register/containers/register.component';

@NgModule({
    declarations: [PublicComponent, LandingComponent, LoginComponent, LandingComponent, RegisterComponent],
    imports: [CommonModule,PublicRoutingModule, SharedModule],
})
export class PublicModule {}
