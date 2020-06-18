import {User} from 'src/app/model/user';


export class RegisterUser {
  static readonly type = '[User API] Register User';

  constructor(public user: User) {
  }
}

export class DeleteUser {
  static readonly type = '[User API] Delete User';

  constructor(public id: string) {
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

export class GetUser {
  static readonly type = '[User API] Get User';

  constructor(public id: string) {
  }
}

export class GetUsers {
  static readonly type = '[User API] Get Users';

  constructor() {
  }
}

