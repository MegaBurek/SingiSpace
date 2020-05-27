import {User} from 'src/app/model/user';
import {State, Selector, Action, StateContext} from '@ngxs/store';
import {tap} from 'rxjs/operators';
import {UserAccService} from 'src/app/services/users/user-acc.service';
import {GetUser, GetUsers, DeleteUser, RegisterUser} from './user.actions';


export class UserStateModel {
  users: User[];
  selectedUser: User;
}

@State<UserStateModel>({
  name: 'user',
  defaults: {
    users: [],
    selectedUser: {
      id: null, name: null, surname: null, username: null, password: null, email: null, dob: null, page_subs: null,
      theme_subs: null, imgUrl: null, permission: null, provider: null
    }
  }
})
export class UserState {
  constructor(
    private userAccService: UserAccService
  ) {
  }

  @Selector()
  static getUsers(state: UserStateModel) {
    return state.users;
  }

  @Selector()
  static getUser(state: UserStateModel) {
    return state.selectedUser;
  }

  @Action(GetUsers)
  getUsers({patchState}: StateContext<UserStateModel>) {
    return this.userAccService.getUsers().pipe(tap((newUsers => {
      patchState({
        users: newUsers
      });
    })));
  }

  @Action(GetUser)
  getUser({patchState}: StateContext<UserStateModel>, {id}: GetUser) {
    return this.userAccService.getUserByID(id).pipe(tap((user => {
      patchState({
        selectedUser: user
      });
    })));
  }

  @Action(DeleteUser)
  deleteUser({getState, patchState}: StateContext<UserStateModel>, {id}: DeleteUser) {
    return this.userAccService.removeUser(id).pipe(tap((user) => {
      const state = getState();
      const filteredUsers = state.users.filter(user => user.id !== id);
      patchState({
        users: [...filteredUsers]
      });
    }));
  }

  // addUpdateUser action

  @Action(RegisterUser)
  addUser({getState, patchState}: StateContext<UserStateModel>, {user}: RegisterUser) {
    return this.userAccService.registerUser(user).pipe(tap((resultUser) => {
      const state = getState();
      patchState({
        users: [...state.users, resultUser]
      });
    }));
  }


}

