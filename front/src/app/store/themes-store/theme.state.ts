import {Theme} from '../../model/theme';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {ThemesService} from '../../services/themes/themes.service';
import {tap} from 'rxjs/operators';
import {CreatePost, CreateTheme, DeleteTheme, GetTheme, GetThemeByName, GetUserThemeSubs, SelectTheme, UpdateTheme} from './theme.action';
import {PostsService} from '../../services/posts/posts.service';
import {User} from '../../model/user';

export class ThemeStateModel {
  subbedThemes: Theme[];
  myThemes: Theme[];
  // themeOwner: User;
  // recommended_themes
  selectedTheme: Theme;
}

@State<ThemeStateModel>({
  name: 'theme',
  defaults: {
    subbedThemes: [],
    myThemes: [],
    // themeOwner: {
    //
    // },
    selectedTheme: {
      id: null, name: null, desc: null, feed: null, members: null, owner: null
    }
  }
})

export class ThemeState {

  constructor(
    private themesService: ThemesService,
    private postsService: PostsService
  ) {
  }

  @Selector()
  static selectTheme(state: ThemeStateModel) {
    return state.selectedTheme;
  }

  @Selector()
  static getSelectedThemeFeed(state: ThemeStateModel) {
    return state.selectedTheme.feed;
  }

  @Selector()
  static getUserThemeSubs(state: ThemeStateModel) {
    return state.subbedThemes;
  }

  @Action(GetTheme)
  getTheme({patchState}: StateContext<ThemeStateModel>, {id}: GetTheme) {
    return this.themesService.getThemeByID(id).pipe(tap((theme => {
      patchState({
        selectedTheme: theme
      });
    })));
  }



  @Action(SelectTheme)
  selectTheme({getState, patchState}: StateContext<ThemeStateModel>, {theme}: SelectTheme) {
    patchState({
      selectedTheme: theme
    });
  }

  // @Action(GetThemeByName)
  // getThemeByName({patchState}: StateContext<ThemeStateModel>, {name}: GetThemeByName) {
  //   return this.t
  // }

  @Action(CreatePost)
  createPost({getState, patchState}: StateContext<ThemeStateModel>, {id}: CreatePost, {post}: CreatePost) {
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
  deleteTheme({getState, patchState}: StateContext<ThemeStateModel>, {id}: DeleteTheme) {
    return this.themesService.deleteThemeByID(id).pipe(tap((theme) => {
      const state = getState();
      const filteredThemes = state.myThemes.filter(theme => theme.id !== id);
      patchState({
        myThemes: [...filteredThemes]
      });
    }));
  }

  @Action(UpdateTheme)
  updateTheme({getState, patchState}: StateContext<ThemeStateModel>, {id, theme}: UpdateTheme) {
    return this.themesService.editTheme(id, theme).pipe(tap((theme) => {
      const state = getState();
      const filteredThemes = state.myThemes.filter(theme => theme.id !== id);
      patchState({
        myThemes: [...filteredThemes]
      });
    }));
  }

  @Action(CreateTheme)
  addTheme({getState, patchState}: StateContext<ThemeStateModel>, {theme}: CreateTheme) {
    return this.themesService.createTheme(theme).pipe(tap((resultTheme) => {
      const state = getState();
      patchState({
        myThemes: [...state.myThemes, resultTheme]
      });
    }));
  }

  @Action(GetUserThemeSubs)
  getUserThemeSubs({patchState}: StateContext<ThemeStateModel>, {id}: GetUserThemeSubs) {
    return this.themesService.getUserThemeSubs(id).pipe(tap((resultThemes) => {
      patchState({
        subbedThemes: resultThemes
      });
    }));
  }


}
