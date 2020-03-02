import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LandingPageComponent } from '../app/landing-page/landing-page.component'
import { HomeComponent } from './core/home-layout/home.component'



const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LandingPageComponent, data: { animation: { value: 'LoginPage' }} },
  { path: 'home', component: HomeComponent, data: { animation: { value: 'HomePage' }} }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
