import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UsersManagementFormComponent } from '../components/users-management-form/users-management-form.component';
import { AuthService } from 'src/app/core/services/auth.service';
import { Subject,takeUntil } from 'rxjs';

@Component({
  selector: 'app-users-management',
  templateUrl: './users-management.component.html',
  styleUrls: ['./users-management.component.css']
})
export class UsersManagementComponent {
isAdmin:boolean = false;
private onDestroy$ = new Subject<void>();
constructor(public dialog: MatDialog,private authService:AuthService) {
    this.authService.checkAdmin().pipe(
        takeUntil(this.onDestroy$)
    ).subscribe(
        (result) => {
            this.isAdmin = result;
        }
    )
 }


    openForm() {
        const dialogRef = this.dialog.open(UsersManagementFormComponent,{
            minWidth: '500px',
        });
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }

}


