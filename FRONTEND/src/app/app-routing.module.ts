import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/authentication/auth.guard';

const routes: Routes = [
    {
        path: 'login',
        loadChildren: () => import('./core/authentication/login/login.module').then(m => m.LoginModule)
    },
    {
        path: 'register',
        loadChildren: () => import('./core/authentication/register/register.module').then(m => m.RegisterModule)
    },
    {
        path: 'home',
        loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
        canLoad: [AuthGuard]
    },
    {
        path: 'route-view',
        loadChildren: () => import('./features/route-view/route-view.module').then(m => m.RouteViewModule),
        canLoad: [AuthGuard]
    },
    {
        path: 'route-creator',
        loadChildren: () => import('./features/route-creator/route-creator.module').then(m => m.RouteCreatorModule),
        canLoad: [AuthGuard]
    },
    {
        path: 'user-management',
        loadChildren: () => import('./features/user-management/user-management.module').then(m => m.UserManagementModule),
        canLoad: [AuthGuard]
    },
    {
        path: 'itinerary',
        loadChildren: () => import('./features/itinerary/itinerary.module').then(m => m.ItineraryModule),
        canLoad: [AuthGuard]
    },
    {
        path: 'account',
        loadChildren: () => import('./features/account/account.module').then(m => m.AccountModule),
        canLoad: [AuthGuard]
    },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/login'
    }
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
