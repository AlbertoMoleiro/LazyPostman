import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-users-management-form',
    templateUrl: './users-management-form.component.html',
    styleUrls: ['./users-management-form.component.css'],
})
export class UsersManagementFormComponent {
    addEmployeeForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this.addEmployeeForm = this.formBuilder.group({
          employeeName: ['', Validators.required],
          apellido1: ['', Validators.required],
          apellido2: ['', Validators.required],
          employeeCode: ['', Validators.required]
        });
    }
    onSubmit() {
        if (this.addEmployeeForm.invalid) {
            return;
        }

        this.addEmployeeForm.reset();
    }
}
