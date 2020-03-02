import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import decode from 'jwt-decode';
import { Subject } from 'rxjs';


class LoginRes {
  token: string;
  role: string;
  username: string;
}

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  loggedInStatusChanged = new Subject<boolean>();

  constructor(private http: HttpClient, private router: Router) { }

  login(username: string, password: string) : Boolean {
    this.http.post<{ token: string }>('http://localhost:8080/authLogin/', { username: username, password: password }).subscribe(response => {
      if (response.token) {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/home']);
        return true;
      }
      return false;
    });
    return false;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
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




}