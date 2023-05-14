import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';


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
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        PublicRoutingModule,
    ],
})
export class PublicModule {}
