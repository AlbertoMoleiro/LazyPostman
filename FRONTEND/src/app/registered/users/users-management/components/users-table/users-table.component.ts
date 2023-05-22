import { Component } from '@angular/core';

export interface User {
  id:number;
  nombre: string;
  apellido1:string;
  apellido2:string;
  codigo_empleado:string;
  rol: string;
}

const ELEMENT_DATA: User[] = [
  {id:1,nombre:'Juan', apellido1: 'Pérez', apellido2:'Sanz',codigo_empleado:'JuanPS',rol:'rol1'},
  {id:2,nombre:'María', apellido1: 'García', apellido2:'Jeréz',codigo_empleado:'MariGJ',rol:'rol2'},
  {id:3,nombre:'Ana', apellido1: 'Díaz', apellido2:'Fernández',codigo_empleado:'AnaDF',rol:'rol2'},
  {id:4,nombre:'Emilio', apellido1: 'López', apellido2:'Martínez',codigo_empleado:'EmiLM',rol:'rol3'},
  {id:5,nombre:'Ernesto', apellido1: 'Florez', apellido2:'Cruz',codigo_empleado:'ErneFC',rol:'rol3'},
];

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent {
  displayedColumns: string[] = ['nombre', 'apellido1', 'apellido2','codigo_empleado','rol','asignar','promociona'];
  dataSource = ELEMENT_DATA;

  assign(id: number) {

  }

  promote(id: number) {
    
  }
}
