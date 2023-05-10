import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouteViewComponent } from './route-view.component';

const routes: Routes = [{ path: '', component: RouteViewComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RouteViewRoutingModule { }
