import { User } from 'src/app/model/user';


export class RegisterUser {
    static readonly type = '[User API] Register User';
    constructor(public user: User){}
}

export class DeleteUser {
    static readonly type = '[User API] Delete User';
    constructor(public id: String){}
}

export class GetUser {
    static readonly type = '[User API] Get User';
    constructor(public id: String){}
}

export class GetUsers {
    static readonly type = '[User API] Get Users';
    constructor(){}
}

