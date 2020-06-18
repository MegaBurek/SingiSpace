import {Page} from '../../model/page';
import {Post} from '../../model/post';

export class CreatePage {
  static readonly type = '[Page API] Create Page';

  constructor(public page: Page) {
  }
}

export class CreatePost {
  static readonly type = '[Page API] Create Post';

  constructor(public post: Post, public id: string) {
  }
}

export class DeletePage {
  static readonly type = '[Page API] Delete Page';

  constructor(public id: string) {
  }
}

export class GetPage {
  static readonly type = '[Page API] Get Page';
  constructor(public id: string) {
  }
}

export class UpdatePage {
  static readonly type = '[Page API] Update Page';
  constructor(public page: Page, public id: string) {
  }
}

export class GetUserPageSubs {
  static readonly type = '[Page API] Get Page Subs';
  constructor(public id: string) {
  }
}
