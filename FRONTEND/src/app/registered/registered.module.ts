import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { HomeComponent } from './home/containers/home.component';
import { RegisteredComponent } from './registered.component';
import { RegisteredRoutingModule } from './registered-routing.module';
import { SharedModule } from '../core/shared/shared.module';

@NgModule({
    declarations: [RegisteredComponent, HomeComponent],
    imports: [CommonModule, RegisteredRoutingModule, SharedModule],
})
export class RegisteredModule {}
