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
import {CreatePage, DeletePage, GetPage, GetUserPageSubs, UpdatePage} from './page.actions';
import {CreatePost, CreateTheme, DeleteTheme, GetTheme, GetUserThemeSubs, SelectTheme, UpdateTheme} from './theme.action';
import {ThemesService} from '../../services/themes/themes.service';
import {PostsService} from '../../services/posts/posts.service';
import {PagesService} from '../../services/pages/pages.service';


export class UserStateModel {
  myFriends: Friend[];
  selectedUser: User;
  loggedInUser: User;
  subbedPages: Page[];
  myPages: Page[];
  selectedPage: Page;
  subbedThemes: Theme[];
  myThemes: Theme[];
  selectedTheme: Theme;
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
    selectedPage: {
      id: null, name: null, desc: null, feed: null, members: null, owner: null, imgUrl: null, categories: null
    },
    subbedThemes: [],
    myThemes: [],
    selectedTheme: {
      id: null, name: null, desc: null, feed: null, members: null, owner: null, imgUrl: null, categories: null
    }
  }
})
export class UserState {
  constructor(
    private userAccService: UserAccService,
    private friendsService: FriendsService,
    private themesService: ThemesService,
    private pagesService: PagesService,
    private postsService: PostsService
  ) {
  }

  @Selector()
  static getMyFriends(state: UserStateModel) {
    return state.myFriends;
  }

  @Selector()
  static getSelectedTheme(state: UserStateModel) {
    return state.selectedTheme;
  }

  @Selector()
  static getSelectedThemeFeed(state: UserStateModel) {
    return state.selectedTheme.feed;
  }

  @Selector()
  static getUserThemeSubs(state: UserStateModel) {
    return state.subbedThemes;
  }

  @Selector()
  static getSelectedPage(state: UserStateModel) {
    return state.selectedPage;
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
  @Action(GetPage)
  getPage({patchState}: StateContext<UserStateModel>, {id}: GetPage) {
    return this.pagesService.getPageByID(id).pipe(tap((page => {
      patchState({
        selectedPage: page
      });
    })));
  }

  @Action(DeletePage)
  deletePage({getState, patchState}: StateContext<UserStateModel>, {id}: DeletePage) {
    return this.pagesService.deletePageByID(id).pipe(tap((page) => {
      const state = getState();
      const filteredPages = state.myPages.filter(page => page.id !== id);
      patchState({
        myPages: [...filteredPages]
      });
    }));
  }

  @Action(UpdatePage)
  updatePage({getState, patchState}: StateContext<UserStateModel>, {id}: UpdatePage, {page}: UpdatePage) {
    return this.pagesService.editPage(id, page).pipe(tap((page) => {
      const state = getState();
      const filteredPages = state.myPages.filter(page => page.id !== id);
      patchState({
        myPages: [...filteredPages]
      });
    }));
  }

  @Action(CreatePage)
  addPage({getState, patchState}: StateContext<UserStateModel>, {page}: CreatePage) {
    return this.pagesService.createPage(page).pipe(tap((resultPage) => {
      const state = getState();
      patchState({
        myPages: [...state.myPages, resultPage]
      });
    }));
  }

  @Action(GetUserPageSubs)
  getUserPageSubs({patchState}: StateContext<UserStateModel>, {id}: GetUserPageSubs) {
    return this.pagesService.getUserPageSubs(id).pipe(tap((resultPages) => {
      patchState({
        subbedPages: resultPages
      });
    }));
  }

  // Theme actions
  @Action(GetTheme)
  getTheme({patchState}: StateContext<UserStateModel>, {id}: GetTheme) {
    return this.themesService.getThemeByID(id).pipe(tap((theme => {
      patchState({
        selectedTheme: theme
      });
    })));
  }

  @Action(SelectTheme)
  selectTheme({patchState}: StateContext<UserStateModel>, {theme}: SelectTheme) {
    patchState({
      selectedTheme: theme
    });
  }

  @Action(CreatePost)
  createPost({getState, patchState}: StateContext<UserStateModel>, {id}: CreatePost, {post}: CreatePost) {
    console.log('Here is the {post} thing: ' + post + ' id: ' + id);
    return this.postsService.createThemePost(id, post).pipe(tap((theme) => {
      console.log('Here is the tap thing: ' + theme);
      const state = getState();
      const filteredThemes = state.subbedThemes.filter(theme => theme.id !== id);
      patchState({
        subbedThemes: [...filteredThemes]
      });
    }));
  }

  @Action(DeleteTheme)
  deleteTheme({getState, patchState}: StateContext<UserStateModel>, {id}: DeleteTheme) {
    return this.themesService.deleteThemeByID(id).pipe(tap((theme) => {
      const state = getState();
      const filteredThemes = state.myThemes.filter(theme => theme.id !== id);
      patchState({
        myThemes: [...filteredThemes]
      });
    }));
  }

  @Action(UpdateTheme)
  updateTheme({getState, patchState}: StateContext<UserStateModel>, {id, theme}: UpdateTheme) {
    const state = getState();
    const filteredThemes = state.myThemes.filter(theme => theme.id !== id);
    patchState({
      myThemes: [...filteredThemes]
    });
  }

  @Action(CreateTheme)
  addTheme({getState, patchState}: StateContext<UserStateModel>, {theme}: CreateTheme) {
    return this.themesService.createTheme(theme).pipe(tap((resultTheme) => {
      const state = getState();
      patchState({
        myThemes: [...state.myThemes, resultTheme]
      });
    }));
  }

  @Action(GetUserThemeSubs)
  getUserThemeSubs({patchState}: StateContext<UserStateModel>, {id}: GetUserThemeSubs) {
    return this.themesService.getUserThemeSubs(id).pipe(tap((resultThemes) => {
      patchState({
        subbedThemes: resultThemes
      });
    }));
  }

}

