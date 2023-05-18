import { Component } from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent {
 

    cards = [
        {
          title: 'Ruta 1',
          imageUrl: 'https://rentandrollmadrid.com/wp-content/uploads/2020/04/ruta-zona-sureste-600x600.jpg',
          description: 'Es la descripción de la Ruta 1.'          
        },
        {
          title: 'Ruta 2',
          imageUrl: 'https://rentandrollmadrid.com/wp-content/uploads/2020/04/ruta-zona-noreste-600x600.jpg',
          description: 'Es la descripción de la Ruta 2.'          
        },
        {
          title: 'Ruta 3',
          imageUrl: 'https://rentandrollmadrid.com/wp-content/uploads/2020/04/ruta-zona-centro-600x600.jpg',
          description: 'Es la descripción de la Ruta 3.'          
        }
      ];
}

