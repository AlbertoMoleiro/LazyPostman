import { Component } from '@angular/core';
import { Router } from '@angular/router';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

export interface Itinerario {
  calle: string;
  numero: string;
  observaciones: string;
}

const ELEMENT_DATA: Itinerario[] = [
  {calle: 'Cipriano Mera', numero: '1', observaciones: 'LLegada en bus línea 9'},
  {calle: 'Cipriano Mera', numero: '2', observaciones: ''},
  {calle: 'Cipriano Mera', numero: '3', observaciones: ''},
  {calle: 'Cipriano Mera', numero: '4', observaciones: 'Local Sindical CGT'},
  {calle: 'Cipriano Mera', numero: '5', observaciones: ''},
  {calle: 'Cipriano Mera', numero: '6', observaciones: ''},
  {calle: 'Cipriano Mera', numero: '7', observaciones: 'Buzón de alcance'},
  {calle: 'Cipriano Mera', numero: '8', observaciones: ''},
  {calle: 'Cipriano Mera', numero: '9', observaciones: 'Regreso en bus línea 9'},
  {calle: 'Cipriano Mera', numero: '10', observaciones: ''},
  {calle: 'Liberta', numero: '6', observaciones: ''},
  {calle: 'Liberta', numero: '7', observaciones: 'Colegio'},
  {calle: 'Liberta', numero: '8', observaciones: ''},
  {calle: 'Liberta', numero: '9', observaciones: ''},
  {calle: 'Liberta', numero: '10', observaciones: ''},
  {calle: 'Liberta', numero: '11', observaciones: ''},
  {calle: 'Liberta', numero: '12', observaciones: 'Regreso a empresa'},
];

@Component({
  selector: 'app-itinerary',
  templateUrl: './itinerary.component.html',
  styleUrls: ['./itinerary.component.css']
})
export class ItineraryComponent {

  displayedColumns: string[] = ['calle', 'numero', 'observaciones'];
  dataSource = ELEMENT_DATA;

  constructor(private router: Router){

  }

  generatePDF() {
    const doc = new jsPDF('l', 'mm', 'a4');
  
    const tableColumnNames = [['Calle', 'Número', 'Observaciones']];
    const data = ELEMENT_DATA.map((itinerario) => [itinerario.calle, itinerario.numero, itinerario.observaciones]);
  
    autoTable(doc, {
      head: tableColumnNames,
      body: data
    });
  
    doc.save('tabla.pdf');
  }

  goToRute() {
    this.router.navigate(['/registered/route/view']);
}
}
