import {Theme} from '../../model/theme';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {ThemesService} from '../../services/themes/themes.service';
import {tap} from 'rxjs/operators';
import {CreateTheme, DeleteTheme, GetTheme, GetThemeByName, GetUserThemeSubs, SelectTheme, UpdateTheme} from './theme.action';

export class ThemeStateModel {
  subbedThemes: Theme[];
  myThemes: Theme[];
  // recommended_themes
  selectedTheme: Theme;
}

@State<ThemeStateModel>({
  name: 'theme',
  defaults: {
    subbedThemes: [],
    myThemes: [],
    selectedTheme: {
      id: null, name: null, desc: null, feed: null, members: null, owner: null
    }
  }
})

export class ThemeState {

  constructor(
    private themesService: ThemesService
  ) {
  }

  @Selector()
  static selectTheme(state: ThemeStateModel) {
    return state.selectedTheme;
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
  updateTheme({getState, patchState}: StateContext<ThemeStateModel>, {id}: UpdateTheme, {theme}: UpdateTheme) {
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
