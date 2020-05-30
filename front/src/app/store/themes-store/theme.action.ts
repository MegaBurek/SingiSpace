import {Theme} from '../../model/theme';

export class CreateTheme {
  static  readonly type = '[Theme API] Create Theme';
  constructor(public theme: Theme) {
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
  constructor(public theme: Theme, public id: string) {
  }
}

export class GetUserThemeSubs {
  static readonly type = '[Theme API] Get Theme Subs';
  constructor(public id: string) {
  }
}
