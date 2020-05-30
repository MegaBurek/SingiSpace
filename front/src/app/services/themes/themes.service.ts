import {Injectable} from '@angular/core';
import {Theme} from 'src/app/model/theme';
import {HttpClient} from '@angular/common/http';

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

  editTheme(id, theme) {
    return this.http.put<Theme>(this.baseUrl + `/${id}`, theme);
  }

  getUserThemeSubs(id) {
    return this.http.get<Theme[]>(this.baseUrl + `/user-subscribed` + `/${id}`);
  }

  getThemeByID(id) {
    return this.http.get<Theme>(this.baseUrl + `/${id}`);
  }

  getThemeByName(name) {
    return this.http.get<Theme>(this.baseUrl + `/theme` + `/${name}`);
  }

}
