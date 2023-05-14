
import { Component } from '@angular/core';
import { MatDrawerMode } from '@angular/material/sidenav';
import { BreakpointObserver, BreakpointState } from '@angular/cdk/layout';
import { NavLink } from 'src/app/core/models/interfaces/nav-link.interface';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
    mode: MatDrawerMode = 'side';
    opened: boolean = true;
    links: NavLink[] = [
        { icon: 'home', name: 'Inicio', route: '/registered/home' },
        { icon: 'map', name: 'Crear Ruta', route: '/registered/route/creator' },
        { icon: 'people', name: 'Gestión de Empleados', route: '/registered/user/management' },
        { icon: 'settings', name: 'Configuración', route: '/registered/user/settings' },
        { icon: 'exit_to_app', name: 'Cerrar sesión', route: '/login' }
    ];
    constructor(private breakpointObserver:BreakpointObserver) {}

    ngOnInit(): void {
        this.breakpointObserver
        .observe(['(min-width: 600px)'])
        .subscribe((state: BreakpointState) => {
          this.mode = state.matches ? 'side' : 'over';
            this.opened = state.matches;
        });
    }
}
