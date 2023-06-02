import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';
import { canActivateRole } from 'src/app/core/shared/guards/auth.guard';

const routes: Routes = [
    {path: '', redirectTo: 'settings', pathMatch: 'full'},
    {path: 'settings', component: UserSettingsComponent},
    {path: 'management', component: UsersManagementComponent,canActivate: [canActivateRole]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
