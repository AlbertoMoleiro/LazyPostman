import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



import { HomeComponent } from './home/containers/home.component';
import { RegisteredComponent } from './registered.component';
import { RegisteredRoutingModule } from './registered-routing.module';

@NgModule({
    declarations: [RegisteredComponent, HomeComponent],
    imports: [CommonModule, RegisteredRoutingModule],
})
export class RegisteredModule {}
