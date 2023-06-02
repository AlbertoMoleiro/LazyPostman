import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateChildFn, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { catchError, map, of } from 'rxjs';
import { AuthService } from '../../services/auth.service';


export const canActivate: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    return authService.checkLogin().pipe(
        map((result) => { result ? true : router.navigate(['/login']); return result; }),
        catchError(() => {
            router.navigate(['/login']);
            return of(false);
        })
    );
};

//CanActivate by Role
export const canActivateManager: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    return authService.checkManager().pipe(
        map((result) => { result ? true : router.navigate(['/registered/home']); return result; }),
        catchError(() => {
            router.navigate(['/registered/home']);
            return of(false);
        })
    );
};

export const canActivateChild: CanActivateChildFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => canActivate(route, state);
