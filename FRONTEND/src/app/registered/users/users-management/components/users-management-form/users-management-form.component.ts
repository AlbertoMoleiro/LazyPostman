import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/core/models/interfaces/user.interface';
import { UsersService } from 'src/app/core/services/users.service';


@Component({
    selector: 'app-users-management-form',
    templateUrl: './users-management-form.component.html',
    styleUrls: ['./users-management-form.component.css'],
})
export class UsersManagementFormComponent {
    addEmployeeForm: FormGroup;

    constructor(private formBuilder: FormBuilder,private usersService:UsersService,public dialogRef: MatDialogRef<UsersManagementFormComponent>) {
        this.addEmployeeForm = this.formBuilder.group({
          employeeName: ['', Validators.required],
          apellido1: ['', Validators.required],
          apellido2: ['', Validators.required],
          phoneNumber: ['', Validators.required],
          managerId: ['', Validators.required],
          login: ['', Validators.required],
        idRole: ['', Validators.required]
        });
    }
    onSubmit() {
        const user :User={
            id: null,
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
}
