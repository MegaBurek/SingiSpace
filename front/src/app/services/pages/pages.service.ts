import {Injectable} from '@angular/core';
import {Page} from '../../model/page';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PagesService {

  private baseUrl = 'http://localhost:8080/page';

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

  getPageByID(id) {
    return this.http.get<Page>(this.baseUrl + `/${id}`);
  }

  getUserPageSubs(id) {
    return this.http.get<Page[]>(this.baseUrl + `/user-subscribed`, id);
  }

}
