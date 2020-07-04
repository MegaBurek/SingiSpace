import {User} from 'src/app/model/user';
import {State, Selector, Action, StateContext} from '@ngxs/store';
import {tap} from 'rxjs/operators';
import {UserAccService} from 'src/app/services/users/user-acc.service';
import {
  SetLoggedIn,
  GetUserFriends,
  GetUserViewByUsername
} from './user.actions';
import {Friend} from '../../model/friend';
import {FriendsService} from '../../services/friends/friends.service';
import {Page} from '../../model/page';
import {Theme} from '../../model/theme';
import {CreatePage, GetUserOwnedPages, GetUserPageSubs} from './page.actions';
import {CreateTheme, GetUserOwnedThemes, GetUserThemeSubs} from './theme.action';
import {ThemesService} from '../../services/themes/themes.service';
import {PagesService} from '../../services/pages/pages.service';


export class UserStateModel {
  myFriends: Friend[];
  selectedUser: User;
  loggedInUser: User;
  subbedPages: Page[];
  myPages: Page[];
  subbedThemes: Theme[];
  myThemes: Theme[];

}

@State<UserStateModel>({
  name: 'user',
  defaults: {
    myFriends: [],
    selectedUser: {
      id: null, name: null, surname: null, username: null, password: null, email: null, dob: null, page_subs: null,
      theme_subs: null, imgUrl: null, permission: null, provider: null, friends: null
    },
    loggedInUser: {
      id: null, name: null, surname: null, username: null, password: null, email: null, dob: null, page_subs: null,
      theme_subs: null, imgUrl: null, permission: null, provider: null, friends: null
    },
    subbedPages: [],
    myPages: [],
    subbedThemes: [],
    myThemes: []
  }
})
export class UserState {
  constructor(
    private userAccService: UserAccService,
    private friendsService: FriendsService,
    private themesService: ThemesService,
    private pagesService: PagesService
  ) {
  }

  @Selector()
  static getUserOwnedThemes(state: UserStateModel) {
    return state.myThemes;
  }

  @Selector()
  static getUserOwnedPages(state: UserStateModel) {
    return state.myPages;
  }

  @Selector()
  static getMyFriends(state: UserStateModel) {
    return state.myFriends;
  }

  @Selector()
  static getUserThemeSubs(state: UserStateModel) {
    return state.subbedThemes;
  }

  @Selector()
  static getUserPageSubs(state: UserStateModel) {
    return state.subbedPages;
  }

  @Selector()
  static getLoggedInUser(state: UserStateModel) {
    return state.loggedInUser;
  }

  @Selector()
  static getLoggedInUserId(state: UserStateModel) {
    return state.loggedInUser.id;
  }

  @Selector()
  static getSelectedUser(state: UserStateModel) {
    return state.selectedUser;
  }

  @Action(SetLoggedIn)
  setLoggedInUser({patchState}: StateContext<UserStateModel>, {currentUser}: SetLoggedIn) {
    patchState({
      loggedInUser: currentUser
    });
  }

  @Action(GetUserFriends)
  getUserFriends({patchState}: StateContext<UserStateModel>, {id}: GetUserFriends) {
    return this.friendsService.getUserFriends(id).pipe(tap((friends) => {
      patchState({
        myFriends: friends
      });
    }));
  }

  @Action(GetUserViewByUsername)
  getUserViewByUsername({patchState}: StateContext<UserStateModel>, {username}: GetUserViewByUsername) {
    return this.userAccService.getUserByUsername(username).pipe(tap((user) => {
      patchState({
        selectedUser: user
      });
    }));
  }

  // Page actions
  @Action(GetUserPageSubs)
  getUserPageSubs({patchState}: StateContext<UserStateModel>, {id}: GetUserPageSubs) {
    return this.pagesService.getUserPageSubs(id).pipe(tap((resultPages) => {
      patchState({
        subbedPages: resultPages
      });
    }));
  }

  @Action(GetUserOwnedPages)
  getUserOwnedPages({patchState}: StateContext<UserStateModel>, {id}: GetUserOwnedPages) {
    return this.pagesService.getUserOwnedPages(id).pipe(tap((resultPages) => {
      patchState({
        myPages: resultPages
      });
    }));
  }

  @Action(CreatePage)
  addNewPage({patchState, getState}: StateContext<UserStateModel>, {page}: CreatePage) {
    return this.pagesService.createPage(page).pipe(tap((resPage) => {
      const state = getState();
      patchState({
        myPages: [...state.myPages, resPage]
      });
    }));
  }

  // Theme actions
  @Action(GetUserThemeSubs)
  getUserThemeSubs({patchState}: StateContext<UserStateModel>, {id}: GetUserThemeSubs) {
    return this.themesService.getUserThemeSubs(id).pipe(tap((resultThemes) => {
      patchState({
        subbedThemes: resultThemes
      });
    }));
  }

  @Action(GetUserOwnedThemes)
  getUserOwnedThemes({patchState}: StateContext<UserStateModel>, {id}: GetUserOwnedThemes) {
    return this.themesService.getUserOwnedThemes(id).pipe(tap((resultThemes) => {
      patchState({
        myThemes: resultThemes
      });
    }));
  }

  @Action(CreateTheme)
  addNewTheme({patchState, getState}: StateContext<UserStateModel>, {theme}: CreateTheme) {
    return this.themesService.createTheme(theme).pipe(tap((resultTheme) => {
      const state = getState();

      patchState({
        myThemes: [...state.myThemes, resultTheme]
      });
    }));
  }

}

