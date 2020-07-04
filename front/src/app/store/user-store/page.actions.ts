import {Page} from '../../model/page';

export class GetUserPageSubs {
  static readonly type = '[Page API] Get Page Subs';
  constructor(public id: string) {
  }
}

export class CreatePage {
  static readonly type = '[Page API] Create Page';

  constructor(public page: Page) {
  }
}

export class GetUserOwnedPages {
  static readonly type = '[Page API] Get User-Owned Pages';

  constructor(public id: string) {
  }
}
