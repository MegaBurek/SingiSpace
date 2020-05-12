import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserAccService {

  private baseUrl = 'http://localhost:8080/user';

  constructor(
    private http: HttpClient
  ) { }

  registerUser(user) {

  }

  getUserByID(id) {
    return this.http.get<User>(this.baseUrl + `/${id}`);
  }
}
