import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PublicComponent } from './public.component';
import { LoginComponent } from './login/containers/login.component';
import { RegisterComponent } from './register/containers/register.component';



const routes: Routes = [
        {path: '', component: PublicComponent, children: [
            {path: '', redirectTo: 'login', pathMatch: 'full'},
            {path: 'login', component: LoginComponent},
            {path: 'register', component: RegisterComponent}]
        },
    ];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule
  ]
})
export class PublicRoutingModule { }
