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
          employeeCode: ['', Validators.required]
        });
    }
    onSubmit() {
        const user :User={
            idUser: null,
            name: this.addEmployeeForm.value.employeeName,
            lastName1: this.addEmployeeForm.value.apellido1,
            lastName2: this.addEmployeeForm.value.apellido2,
            username: this.addEmployeeForm.value.employeeCode,
            rol: this.addEmployeeForm.value.rol
        };
        this.usersService.addUser(user);
        this.dialogRef.close();
    }
}
