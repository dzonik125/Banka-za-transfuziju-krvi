import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const requiredRole = route.data['roles'] as Array<string>;
        if (!requiredRole.includes(this.authService.getUserRole())) {
            this.router.navigate(['auth/login'], { queryParams: { returnUrl: state.url } });
            return false;
        }
            
        return true;
    }

}
