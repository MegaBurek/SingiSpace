import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Friend} from '../../model/friend';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  private baseUrl = 'http://localhost:8080/user';

  constructor(
    private http: HttpClient
  ) {
  }

  getUserFriends(id) {
    return this.http.get<Friend[]>(this.baseUrl + `/friends` + `/${id}`);
  }

  getFriend(id) {
    return this.http.get<Friend>(this.baseUrl + `/friend` + `/${id}`);
  }

}
