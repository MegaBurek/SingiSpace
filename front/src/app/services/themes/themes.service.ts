import {Injectable} from '@angular/core';
import {Theme} from 'src/app/model/theme';
import {HttpClient} from '@angular/common/http';
import {Post} from '../../model/post';

@Injectable({
  providedIn: 'root'
})
export class ThemesService {

  private baseUrl = 'http://localhost:8080/themes';

  constructor(
    private http: HttpClient
  ) {
  }

  createTheme(theme) {
    return this.http.post<Theme>(this.baseUrl + `/create`, theme); // add id of page to user page subs
  }

  deleteThemeByID(id) {
    return this.http.delete<string>(this.baseUrl + `/${id}`);
  }

  getThemeFeed(name) {
    return this.http.get<Post[]>(this.baseUrl + `/feed` + `/${name}`);
  }

  editTheme(id, theme) {
    return this.http.put<Theme>(this.baseUrl + `/${id}`, theme);
  }

  subscribe(name) {
    return this.http.post<string>(this.baseUrl + `/subscribe`, name);
  }

  getUserThemeSubs(id) {
    return this.http.get<Theme[]>(this.baseUrl + `/user-subscribed` + `/${id}`);
  }

  getUserOwnedThemes(id) {
    return this.http.get<Theme[]>(this.baseUrl + `/owner` + `/${id}`);
  }

  getThemeByID(id) {
    return this.http.get<Theme>(this.baseUrl + `/${id}`);
  }

  getThemeByName(name) {
    return this.http.get<Theme>(this.baseUrl + `/theme` + `/${name}`);
  }

}
