import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

import { HomeComponent } from './core/home-layout/home.component';
import { MainFeedComponent } from './core/main-feed/main-feed.component';
import { ChatBarComponent } from './core/chat-bar/chat-bar.component';
import { SubscribedBarComponent } from './core/subscribed-bar/subscribed-bar.component';
import { GroupsTabComponent } from './core/subscribed-bar/groups-tab/groups-tab.component';
import { PagesTabComponent } from './core/subscribed-bar/pages-tab/pages-tab.component';
import { SignInComponent } from '../app/core/auth/sign-in/sign-in.component';
import { RegisterComponent } from './core/auth/register/register.component';
import { NavbarComponent } from './core/nav/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MainFeedComponent,
    ChatBarComponent,
    SubscribedBarComponent,
    GroupsTabComponent,
    PagesTabComponent,
    SignInComponent,
    RegisterComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
