import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './core/shared/components/not-found/not-found.component';
import { canActivate } from './core/shared/guards/auth.guard';

const routes: Routes = [
    {
        path: '',
        loadChildren: () =>
            import('./public/public.module').then((m) => m.PublicModule),
    },
    {
        path: 'registered',
        loadChildren: () =>
            import('./registered/registered.module').then((m) => m.RegisteredModule),canActivate:[canActivate]
    },
    { path: '**', component: NotFoundComponent },
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
