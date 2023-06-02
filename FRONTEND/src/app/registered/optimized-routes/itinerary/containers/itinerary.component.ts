import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { Itinerary } from 'src/app/core/models/interfaces/itinerary.interface';
import { RouteManagementService } from 'src/app/core/services/route-management.service';

@Component({
    selector: 'app-itinerary',
    templateUrl: './itinerary.component.html',
    styleUrls: ['./itinerary.component.css']
})
export class ItineraryComponent {

    displayedColumns: string[] = ['province','town','postCode','roadName', 'roadNumber'];
    dataSource:Itinerary[] = [];
    idRoute:number=0;
    constructor(private router: Router, private routeManagement:RouteManagementService,private activatedRoute:ActivatedRoute) {

        this.idRoute=this.activatedRoute.snapshot.params['id'];
        this.routeManagement.getItinerary(this.idRoute).subscribe(
            (itineraries: Itinerary[]) => {
                this.dataSource = itineraries;
            });
/*             this.dataSource = [
                {
                    province:"Madrid",
                    town:{
                        cdmuni:68,
                        dsmuni:"Guadarrama"
                    },
                    postCode:28440,
                    roadType:"Calle",
                    roadName:"ABEDUL",
                    roadNumber:1,
                },
                {
                    province:"Madrid",
                    town:{
                        cdmuni:68,
                        dsmuni:"Guadarrama"
                    },
                    postCode:28440,
                    roadType:"Calle",
                    roadName:"ABEDUL",
                    roadNumber:1,
                }] */

    }

    generatePDF() {
        const doc = new jsPDF('l', 'mm', 'a4');

        const tableColumnNames = [['Provincia','Localidad','Código Postal','Calle', 'Número']];
        const data = this.dataSource.map((itinerario) => [itinerario.province,itinerario.town.dsmuni,itinerario.postCode, itinerario.roadType +" "+itinerario.roadName, itinerario.roadNumber]);

        autoTable(doc, {
            head: tableColumnNames,
            body: data
        });

        doc.save('tabla.pdf');
    }

    goToRute() {
        this.router.navigate(['/registered/route/view',this.idRoute]);
    }
}
