import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-users-management',
  templateUrl: './users-management.component.html',
  styleUrls: ['./users-management.component.css']
})
export class UsersManagementComponent {

constructor(public dialog: MatDialog) { }


    openForm() {
        const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
            data: {name: this.name, animal: this.animal},
          }
    }

}
