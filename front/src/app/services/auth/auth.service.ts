import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import decode from 'jwt-decode';
import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/app/model/user';


@Injectable({ providedIn: 'root' })
export class AuthService {

  roleChanged = new Subject<any[]>();
  private loggedIn = new BehaviorSubject<boolean>(false);

  private clientUrl = `http://localhost:8080/login`;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
  }

  login(username: string, password: string) {
    // tslint:disable-next-line:max-line-length
    this.http.post<{ accessToken: string }>(this.clientUrl, { username, password }).subscribe(response => {
      if (response.accessToken) {
        localStorage.setItem('accessToken', response.accessToken);
        this.roleChanged.next(this.getCurrentRoles());
        this.router.navigate(['/home']);
        return true;
      } else {
        return false;
      }
    });
    return;
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.router.navigate(['/login']);
    this.roleChanged.next([]);
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

  isAdmin() {
    const token = localStorage.getItem('accessToken');
    if (token) {
      if (decode(token).sub == 'ROLE_ADMIN') {
        return true;
      } else {
        return false;
      }
    }
  }

  getCurrentUser(id: string) {
    return this.http.get<User>(this.clientUrl + `${id}`);
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
