import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouteCreatorComponent } from './route-creator.component';

const routes: Routes = [{ path: '', component: RouteCreatorComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RouteCreatorRoutingModule { }
