import {Injectable} from '@angular/core';
import {Page} from '../../model/page';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../auth/auth.service';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PagesService {

  private baseUrl = 'http://localhost:8080/pages';

  constructor(
    private http: HttpClient
  ) {
  }

  createPage(page) {
    return this.http.post<Page>(this.baseUrl + `/create`, page); // add id of page to user page subs
  }

  deletePageByID(id) {
    return this.http.delete<string>(this.baseUrl + `/${id}`);
  }

  editPage(id, page) {
    return this.http.put<Page>(this.baseUrl + `/${id}`, page);
  }

  getUserPageSubs(id) {
    return this.http.get<Page[]>(this.baseUrl + `/user-subscribed` + `/${id}`);
  }

  getPageByID(id) {
    return this.http.get<Page>(this.baseUrl + `/${id}`);
  }

}
