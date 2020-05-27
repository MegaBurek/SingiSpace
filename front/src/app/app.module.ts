import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { NgxsModule } from '@ngxs/store';

import { AppComponent } from './app.component';
import { HomeComponent } from './core/home/home.component';
import { MainFeedComponent } from './core/main-feed/main-feed.component';
import { PagesTabComponent } from './core/pages-tab/pages-tab.component';
import { SignInComponent } from './shared/sign-in/sign-in.component';
import { RegisterComponent } from './shared/register/register.component';
import { FriendsTabComponent } from './core/friends-tab/friends-tab.component';
import { ThemesTabComponent } from './core/themes-tab/themes-tab.component';
import { GroupsTabComponent } from './core/groups-tab/groups-tab.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserState } from './store/user-store/user.state';
import { NotifierService } from 'angular-notifier';
import { PageCreationComponent } from './shared/page-creation/page-creation.component';
import {PageState} from './store/page-store/page.state';
import { MyPagesComponent } from './shared/my-pages/my-pages.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MainFeedComponent,
    PagesTabComponent,
    SignInComponent,
    RegisterComponent,
    FriendsTabComponent,
    ThemesTabComponent,
    GroupsTabComponent,
    UserProfileComponent,
    PageCreationComponent,
    MyPagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    NgxsModule.forRoot([
      UserState,
      PageState
    ])

  ],
  providers: [ NotifierService],
  bootstrap: [AppComponent]
})
export class AppModule { }
