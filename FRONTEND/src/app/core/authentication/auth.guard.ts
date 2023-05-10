import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export function AuthGuard() {
    const authService = inject(AuthService);
    const router = inject(Router);

    const isAuthenticated = authService.isLoggedIn();
    if (isAuthenticated) {
      return true;
    } else {
      router.navigate(['/login']);
      return false;
    }
  }



