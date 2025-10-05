import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const adminGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Proveravamo da li je korisnik admin
  if (authService.isAdmin()) {
    // Ako jeste, dozvoli pristup ruti.
    return true;
  }

  // Ako nije admin, preusmeri ga na početnu stranicu.
  // Ne prikazujemo nikakvu grešku, samo ga vratimo na sigurno.
  router.navigate(['/home']);
  return false;
};
