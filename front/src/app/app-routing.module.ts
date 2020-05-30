import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SignInComponent } from './shared/sign-in/sign-in.component';
import { HomeComponent } from './core/home/home.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {RegisterComponent} from './shared/register/register.component';
import {PageCreationComponent} from './shared/page-creation/page-creation.component';
import {ThemeCreationComponent} from './shared/theme-creation/theme-creation.component';
import {PageDetailComponent} from './shared/page-detail/page-detail.component';
import {ThemeDetailComponent} from './shared/theme-detail/theme-detail.component';



const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: SignInComponent, data: { animation: { value: 'LoginPage' }} },
  { path: 'register', component: RegisterComponent, data: { animation: { value: 'RegisterPage' }} },
  { path: 'home', component: HomeComponent, data: { animation: { value: 'HomePage' }} },
  { path: 'user-profile', component: UserProfileComponent, data: { animation: { value: 'UserProfilePage'}}},
  { path: 'create-page', component: PageCreationComponent, data: { animation: { value: 'PageCreationPage'}}},
  { path: 'create-theme', component: ThemeCreationComponent, data: { animation: { value: 'ThemeCreationPage'}}},
  { path: 'page/:pageName', component: PageDetailComponent, data: {animation: {value: 'PageDetailPage'}}},
  { path: 'theme/:themeName', component: ThemeDetailComponent, data: {animation: {value: 'ThemeDetailPage'}}}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
