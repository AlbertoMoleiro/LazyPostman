import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisteredComponent } from './registered.component';
import { HomeComponent } from './home/containers/home.component';

const routes: Routes = [
    {
        path: '',
        component: RegisteredComponent,
        children: [
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            { path: 'home', component: HomeComponent },
            {
                path: 'user',
                loadChildren: () =>
                    import('./users/users.module').then((m) => m.UsersModule),
            },
            {
                path: 'route',
                loadChildren: () =>
                    import('./optimized-routes/optimized-routes.module').then(
                        (m) => m.OptimizedRoutesModule
                    ),
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class RegisteredRoutingModule {}
