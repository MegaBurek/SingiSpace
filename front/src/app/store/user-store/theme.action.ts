import {Theme} from '../../model/theme';
import {Post} from '../../model/post';

export class CreateTheme {
  static readonly type = '[Theme API] Create Theme';

  constructor(public theme: Theme) {
  }
}

export class CreatePost {
  static readonly type = '[Theme API] Create Post';

  constructor(public id: string, public post: Post) {
  }
}

export class GetThemeByName {
  static readonly type = '[Theme API] Get Theme By Name';

  constructor(public name: string) {
  }
}

export class DeleteTheme {
  static readonly type = '[Theme API] Delete Theme';

  constructor(public id: string) {
  }
}

export class GetTheme {
  static readonly type = '[Theme API] Get Theme';

  constructor(public id: string) {
  }
}

export class SelectTheme {
  static readonly type = '[Theme API] Select Theme';

  constructor(public theme: Theme) {
  }
}

export class UpdateTheme {
  static readonly type = '[Theme API] Update Theme';

  constructor(public id: string, public theme: Theme) {
  }
}

export class GetUserThemeSubs {
  static readonly type = '[Theme API] Get Theme Subs';

  constructor(public id: string) {
  }
}
