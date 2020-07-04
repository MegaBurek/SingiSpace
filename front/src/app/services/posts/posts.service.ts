import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Theme} from '../../model/theme';
import {Page} from '../../model/page';
import {Post} from '../../model/post';
import {ImgService} from '../img.service';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  private baseUrl = 'http://localhost:8080/post';

  constructor(
    private http: HttpClient
  ) {
  }

  createThemePost(id, post) {
    return this.http.post<Theme>(this.baseUrl + `/theme` + `/${id}`, post);
  }

  createPagePost(id, post) {
    return this.http.post<Post>(this.baseUrl + `/page` + `/${id}`, post);
  }

  like(id, userId) {
    return this.http.post<string>(this.baseUrl + `/like` + `/${id}`, userId);
  }

  unlike(id, userId) {
    return this.http.post<string>(this.baseUrl + `/unlike` + `/${id}`, userId);
  }
}
