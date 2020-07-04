import {Theme} from '../../model/theme';

export class GetUserThemeSubs {
  static readonly type = '[Theme API] Get Theme Subs';

  constructor(public id: string) {
  }
}

export class CreateTheme {
  static readonly type = '[Theme API] Create Theme';

  constructor(public theme: Theme) {
  }
}

export class GetUserOwnedThemes {
  static readonly type = '[Theme API] Get User-Owned Themes';

  constructor(public id: string) {
  }
}
