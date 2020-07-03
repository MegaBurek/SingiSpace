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
import {GetUserPageSubs} from './page.actions';
import {GetUserThemeSubs} from './theme.action';
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

  // Theme actions
  @Action(GetUserThemeSubs)
  getUserThemeSubs({patchState}: StateContext<UserStateModel>, {id}: GetUserThemeSubs) {
    return this.themesService.getUserThemeSubs(id).pipe(tap((resultThemes) => {
      patchState({
        subbedThemes: resultThemes
      });
    }));
  }

}

