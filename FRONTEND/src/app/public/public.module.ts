import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicRoutingModule } from './public-routing.module';
import { SharedModule } from '../core/shared/shared.module';

import { PublicComponent } from './public.component';
import { LoginComponent } from './login/containers/login.component';
import { RegisterComponent } from './register/containers/register.component';


@NgModule({
    declarations: [
        PublicComponent,
        LoginComponent,
        RegisterComponent,
    ],
    imports: [
        CommonModule,
        SharedModule,
        PublicRoutingModule,
    ],
})
export class PublicModule {}
