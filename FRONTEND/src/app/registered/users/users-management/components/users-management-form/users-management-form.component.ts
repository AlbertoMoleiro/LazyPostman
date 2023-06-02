import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';
import { User } from 'src/app/core/models/interfaces/user.interface';
import { UsersService } from 'src/app/core/services/users.service';


@Component({
    selector: 'app-users-management-form',
    templateUrl: './users-management-form.component.html',
    styleUrls: ['./users-management-form.component.css'],
})
export class UsersManagementFormComponent {
    addEmployeeForm: FormGroup;
    selected='3';
    private onDestroy$ = new Subject<void>();

    constructor(private formBuilder: FormBuilder,private usersService:UsersService,public dialogRef: MatDialogRef<UsersManagementFormComponent>,@Inject(MAT_DIALOG_DATA) public data: {id: number}) {
        this.addEmployeeForm = this.formBuilder.group({
          employeeName: ['', Validators.required],
          apellido1: ['', Validators.required],
          apellido2: ['', Validators.required],
          phoneNumber: ['', Validators.required],
          managerId: ['', Validators.required],
          login: ['', Validators.required],
        idRole: [this.selected, Validators.required]
        });
    }

    ngOnInit():void{
        if(this.data){
            this.usersService.getUser(this.data.id).pipe(
                takeUntil(this.onDestroy$)
            ).subscribe((user:User)=>{
                this.addEmployeeForm.patchValue({
                    employeeName: user.name,
                    apellido1: user.lastname1,
                    apellido2: user.lastname2,
                    phoneNumber: user.phoneNumber,
                    managerId: user.managerId,
                    login: user.login,
                    idRole: user.idRole?.toString() /* toString cuando no sea nulo */
                });
            });
        }

    }

    onSubmit() {
        const user :User={
            id: this.data?.id,
            name: this.addEmployeeForm.value.employeeName,
            lastname1: this.addEmployeeForm.value.apellido1,
            lastname2: this.addEmployeeForm.value.apellido2,
            phoneNumber: this.addEmployeeForm.value.phoneNumber,
            managerId: this.addEmployeeForm.value.managerId,
            login: this.addEmployeeForm.value.login,
            idRole: this.addEmployeeForm.value.idRole
        };
        this.usersService.addUser(user);
        this.dialogRef.close();
    }

    ngOnDestroy(): void {
        this.onDestroy$.next();
        this.onDestroy$.complete();
    }
}
