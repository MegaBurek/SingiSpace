import {User} from 'src/app/model/user';


export class RegisterUser {
  static readonly type = '[User API] Register User';

  constructor(public user: User) {
  }
}

export class GetUserFriends {
  static readonly type = '[User API] Get User Friends';

  constructor(public id: string) {
  }
}

export class SetLoggedIn {
  static readonly type = '[User API] Set Logged In';

  constructor(public currentUser: User) {
  }
}

export class GetUserViewByUsername {
  static readonly type = '[User API] Get User By Username';

  constructor(public username: string) {
  }
}


