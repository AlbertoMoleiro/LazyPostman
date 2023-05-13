import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './core/shared/components/not-found/not-found.component';

const routes: Routes = [
    {
        path: '',
        loadChildren: () =>
            import('./public/public.module').then((m) => m.PublicModule),
    },
    {
        path: 'registered',
        loadChildren: () =>
            import('./registered/registered.module').then((m) => m.RegisteredModule),
    },
    { path: '**', component: NotFoundComponent },
];
@NgModule({
    imports: [RouterModule.forRoot(routes)], // {useHash: true} para el refresh de la pagina
    exports: [RouterModule],
})
export class AppRoutingModule {}
