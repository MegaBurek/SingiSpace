import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import decode from 'jwt-decode';
import { Subject } from 'rxjs';


@Injectable({ providedIn: 'root' })
export class LoginService {

  roleChanged = new Subject<any[]>();
  loggedInStatusChanged = new Subject<boolean>();

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  login(username: string, password: string) {
    this.http.post<{ token: string }>('http://localhost:8080/login/', { username: username, password: password }).subscribe(response => {
      if (response.token) {
        localStorage.setItem('token', response.token);
        this.roleChanged.next(this.getCurrentRoles());
        this.router.navigate(['/']);
        this.loggedInStatusChanged.next(true);
        return true

      }
      return false
    });
    return false
  }

  logout() {
    this.roleChanged.next([]);
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
    this.loggedInStatusChanged.next(false);
  }

  getCurrentRoles() {
    const token = localStorage.getItem('token');
    const roles = []
    if (token) {
      decode(token).role.forEach(role => {
        roles.push(role.authority);
      });
    }
    return roles;
  }

  getCurrentUser() {
    const token = localStorage.getItem('token');
    if (token) {
      return decode(token).sub;
    }
    return null;
  }

  isLoggedIn() {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }

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
