import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItineraryComponent } from './itinerary/containers/itinerary.component';
import { RouteCreatorComponent } from './route-creator/containers/route-creator.component';
import { RouteViewComponent } from './route-view/containers/route-view.component';

const routes: Routes = [
    {path: '', redirectTo: 'creator', pathMatch: 'full'},
    {path: 'creator', component: RouteCreatorComponent},
    {path:'itinerary', component: ItineraryComponent},
    {path:'view', component: RouteViewComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OptimizedRoutesRoutingModule { }
