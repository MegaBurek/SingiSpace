import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { ToastrModule } from 'ngx-toastr';

import { ModalModule } from './_modal';

import { NgxsModule } from '@ngxs/store';
import { NgxsStoragePluginModule } from '@ngxs/storage-plugin';

import { AppComponent } from './app.component';
import { HomeComponent } from './shared/layout/home/home.component';
import { MainFeedComponent } from './shared/layout/main-feed/main-feed.component';
import { PagesTabComponent } from './shared/components/pages-tab/pages-tab.component';
import { SignInComponent } from './shared/components/sign-in/sign-in.component';
import { RegisterComponent } from './shared/components/register/register.component';
import { FriendsTabComponent } from './shared/components/friends-tab/friends-tab.component';
import { ThemesTabComponent } from './shared/components/themes-tab/themes-tab.component';
import { GroupsTabComponent } from './shared/components/groups-tab/groups-tab.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserState } from './store/user-store/user.state';
import { NotifierService } from 'angular-notifier';
import { PageCreationComponent } from './shared/components/page-creation/page-creation.component';
import {PageState} from './store/page-store/page.state';
import { MyPagesComponent } from './shared/components/my-pages/my-pages.component';
import { NgxsReduxDevtoolsPluginModule} from '@ngxs/devtools-plugin';
import { ThemeCreationComponent } from './shared/components/theme-creation/theme-creation.component';
import {ThemeState} from './store/themes-store/theme.state';
import { ThemeDetailComponent } from './shared/components/theme-detail/theme-detail.component';
import { PageDetailComponent } from './shared/components/page-detail/page-detail.component';

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
    MyPagesComponent,
    ThemeCreationComponent,
    ThemeDetailComponent,
    PageDetailComponent
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
      PageState,
      ThemeState
    ]),
    NgxsReduxDevtoolsPluginModule.forRoot(),
    NgxsStoragePluginModule.forRoot(),
    ModalModule
  ],
  providers: [ NotifierService],
  bootstrap: [AppComponent]
})
export class AppModule { }
