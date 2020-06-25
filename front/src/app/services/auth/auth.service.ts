import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import decode from 'jwt-decode';
import {Subject, BehaviorSubject, Observable} from 'rxjs';
import {User} from 'src/app/model/user';
import {NotificaitionService} from '../notificaition.service';
import {GetUserPageSubs} from '../../store/user-store/page.actions';
import {Store} from '@ngxs/store';
import {GetUserThemeSubs} from '../../store/user-store/theme.action';
import {GetUserFriends, SetLoggedIn} from '../../store/user-store/user.actions';
import {UserAccService} from '../users/user-acc.service';


@Injectable({providedIn: 'root'})
export class AuthService {


  private loginUrl = `http://localhost:8080/login`;

  constructor(
    private notify: NotificaitionService,
    private http: HttpClient,
    private router: Router,
    private store: Store,
    private userAccService: UserAccService,
  ) {
  }

  async login(username: string, password: string) {
    // tslint:disable-next-line:max-line-length
    this.http.post<{ accessToken: string }>(this.loginUrl, {username, password}).subscribe(async response => {
      if (response.accessToken) {
        localStorage.setItem('accessToken', response.accessToken);
        const id = this.getCurrentUserID();
        await this.store.dispatch(new GetUserThemeSubs(id));
        await this.store.dispatch(new GetUserPageSubs(id));
        await this.store.dispatch(new GetUserFriends(id));
        await this.userAccService.getCurrentUser(id).subscribe(logged => {
          this.store.dispatch(new SetLoggedIn(logged));
        });
        await this.notify.showSuccess('Successful Attempt', 'Notification');
        if (this.isAdminLogged()) {
          await this.router.navigate(['/dashboard']);
        } else {
          await this.router.navigate(['/home']);
        }
      }
    }, (err) => {
      console.error(err);
      this.notify.showError('Incorrect Username or Password', 'Notification');
    });
    return;
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    const user = localStorage.getItem('accessToken');
    if (user != null) {
      return true;
    } else {
      return false;
    }
  }

  getCurrentRoles() {
    const token = localStorage.getItem('accessToken');
    const roles = [];
    if (token) {
      decode(token).role.forEach(role => {
        roles.push(role.authority);
      });
    }
    return roles;
  }

  getCurrentUserID() {
    const token = localStorage.getItem('accessToken');
    if (token) {
      return decode(token).sub;
    }
    return null;
  }

  isAdminLogged() {
    const role = this.getCurrentRoles();
    if (role[0] === 'ROLE_ADMIN') {
      return true;
    } else {
      return false;
    }
  }

  isTutor() {
    const token = localStorage.getItem('accessToken');
    if (token) {
      if (decode(token).sub === 'ROLE_TUTOR') {
        return true;
      } else {
        return false;
      }
    }
  }

  getLoggedInUsername() {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      return decode(accessToken).uniq;
    }
    return null;

  }

  // getCurrentUserName() {
  //   const token = localStorage.getItem('token');
  //   if (token) {
  //     return decode(token).sub;
  //   }
  // }

  // confirmRegistration(token) {
  //   return this.http.post(`http://localhost:8080/account-data/confirm-account`, token);
  // }

  // resetPasswordEmail(accountData: AccountData) {
  //   console.log(accountData);
  //   return this.http.post(`http://localhost:8080/account-data/forgot-password`, accountData);
  // }

  // confirmPassword(accountData: AccountData) {
  //   console.log(accountData);
  //   return this.http.post(`http://localhost:8080/account-data/reset-password`, accountData);
  // }

}
