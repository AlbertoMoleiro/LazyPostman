import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UsersManagementFormComponent } from '../components/users-management-form/users-management-form.component';

@Component({
  selector: 'app-users-management',
  templateUrl: './users-management.component.html',
  styleUrls: ['./users-management.component.css']
})
export class UsersManagementComponent {

constructor(public dialog: MatDialog) { }


    openForm() {
        const dialogRef = this.dialog.open(UsersManagementFormComponent,{
            minWidth: '500px',
        });
    }

}
