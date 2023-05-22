import { Component } from '@angular/core';
interface Road {
    id: number;
    town: string;
    postCode: number;
    roadType: string;
    name: string;
    minOdd: number;
    maxOdd: number;
    minEven: number;
    maxEven: number;

}
const ELEMENT_DATA: Road[] = [
    {id:1,town: 'Guadarrama', postCode: 28440, roadType: 'Avenida', name: 'Real', minOdd: 1, maxOdd: 13, minEven: 2, maxEven: 24},
  ];
@Component({
  selector: 'app-route-creator-table',
  templateUrl: './route-creator-table.component.html',
  styleUrls: ['./route-creator-table.component.css']
})

export class RouteCreatorTableComponent {
    displayedColumns: string[] = ['deleteButton','postCode','roadType', 'name', 'minOdd', 'maxOdd', 'minEven', 'maxEven'];
    dataSource = ELEMENT_DATA;

    deleteRoad(id: number) {
    }
}
