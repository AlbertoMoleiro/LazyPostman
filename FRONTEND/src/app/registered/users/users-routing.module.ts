import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserSettingsComponent } from './user-settings/containers/user-settings.component';
import { UsersManagementComponent } from './users-management/containers/users-management.component';

const routes: Routes = [
    {path: '', redirectTo: 'settings', pathMatch: 'full'},
    {path: 'settings', component: UserSettingsComponent},
    {path: 'management', component: UsersManagementComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
