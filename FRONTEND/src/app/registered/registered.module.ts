import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';



import { HomeComponent } from './home/containers/home.component';
import { RegisteredComponent } from './registered.component';
import { RegisteredRoutingModule } from './registered-routing.module';
import { SharedModule } from '../core/shared/shared.module';

@NgModule({
    declarations: [RegisteredComponent, HomeComponent],
    imports: [CommonModule, RegisteredRoutingModule, SharedModule, MatCardModule,MatButtonModule],
})
export class RegisteredModule {}
