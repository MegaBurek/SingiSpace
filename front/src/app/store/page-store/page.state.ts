import {Page} from 'src/app/model/page';
import {State, Selector, Action, StateContext} from '@ngxs/store';
import {tap} from 'rxjs/operators';
import {PagesService} from '../../services/pages/pages.service';
import {CreatePage, DeletePage, GetPage, GetUserPageSubs, UpdatePage} from './page.actions';

export class PageStateModel {
  subbedPages: Page[];
  myPages: Page[];
  // recommended_pages !This can be some AI that recommends pages based on what they search
  selectedPage: Page;
}

@State<PageStateModel>({
  name: 'page',
  defaults: {
    subbedPages: [],
    myPages: [],
    selectedPage: {
      id: null, name: null, desc: null, feed: null, members: null, owner: null
    }
  }
})

export class PageState {

  constructor(
    private pagesService: PagesService
  ) {
  }

  @Selector()
  static getPage(state: PageStateModel) {
    return state.selectedPage;
  }

  @Selector()
  static getUserPageSubs(state: PageStateModel) {
    return state.subbedPages;
  }

  @Action(GetPage)
  getPage({patchState}: StateContext<PageStateModel>, {id}: GetPage) {
    return this.pagesService.getPageByID(id).pipe(tap((page => {
      patchState({
        selectedPage: page
      });
    })));
  }

  @Action(DeletePage)
  deletePage({getState, patchState}: StateContext<PageStateModel>, {id}: DeletePage) {
    return this.pagesService.deletePageByID(id).pipe(tap((page) => {
      const state = getState();
      const filteredPages = state.myPages.filter(page => page.id !== id);
      patchState({
        myPages: [...filteredPages]
      });
    }));
  }

  @Action(UpdatePage)
  updatePage({getState, patchState}: StateContext<PageStateModel>, {id}: UpdatePage, {page}: UpdatePage) {
    return this.pagesService.editPage(id, page).pipe(tap((page) => {
      const state = getState();
      const filteredPages = state.myPages.filter(page => page.id !== id);
      patchState({
        myPages: [...filteredPages]
      });
    }));
  }

  @Action(CreatePage)
  addPage({getState, patchState}: StateContext<PageStateModel>, {page}: CreatePage) {
    return this.pagesService.createPage(page).pipe(tap((resultPage) => {
      const state = getState();
      patchState({
        myPages: [...state.myPages, resultPage]
      });
    }));
  }

  @Action(GetUserPageSubs)
  getUserPageSubs({patchState}: StateContext<PageStateModel>, {id}: GetUserPageSubs) {
    return this.pagesService.getUserPageSubs(id).pipe(tap((resultPages) => {
      patchState({
        subbedPages: resultPages
      });
    }));
  }
}
