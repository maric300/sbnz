import { Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {authGuard} from "./guards/auth.guard";
import {NotificationsComponent} from "./components/notifications/notifications.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'notifications', component: NotificationsComponent},

  {
    path: 'home',
    component: HomeComponent,
    canActivate: [authGuard],
  },

  {path: '', redirectTo: 'login', pathMatch: 'full'},

  {path: '**', redirectTo: 'login'}
];
