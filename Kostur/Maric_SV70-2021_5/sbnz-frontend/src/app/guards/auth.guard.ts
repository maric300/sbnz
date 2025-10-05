import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Proveravamo da li token postoji u local storage-u
  if (authService.getToken()) {
    // Ako postoji, korisnik je ulogovan, dozvoli pristup ruti.
    return true;
  }

  // Ako token ne postoji, preusmeri korisnika na login stranicu.
  router.navigate(['/login']);
  return false;
};
