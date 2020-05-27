import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserAccService {

  private baseUrl = 'http://localhost:8080/user';

  constructor(
    private http: HttpClient
  ) {
  }

  registerUser(user) {
    return this.http.post<User>(this.baseUrl + `/register/learner`, user);
  }

  getUserByID(id) {
    return this.http.get<User>(this.baseUrl + `/${id}`);
  }

  getUsers() {
    return this.http.get<User[]>(this.baseUrl + `/all`);
  }

  editUser(id, user) {
    return this.http.put<User>(this.baseUrl + `/${id}`, user);
  }

  removeUser(id) {
    return this.http.delete<string>(this.baseUrl + `/${id}`);
  }
}
