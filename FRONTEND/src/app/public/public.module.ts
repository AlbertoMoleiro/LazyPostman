import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';




import { PublicRoutingModule } from './public-routing.module';
import { SharedModule } from '../core/shared/shared.module';

import { PublicComponent } from './public.component';
import { LoginComponent } from './login/containers/login.component';
import { LandingComponent } from './landing/containers/landing.component';
import { RegisterComponent } from './register/containers/register.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        PublicComponent,
        LandingComponent,
        LoginComponent,
        LandingComponent,
        RegisterComponent,
    ],
    imports: [
        CommonModule,
        SharedModule,
        ReactiveFormsModule,
        PublicRoutingModule,
    ],
})
export class PublicModule {}
