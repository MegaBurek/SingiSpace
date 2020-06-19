import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  private baseUrl = `http://localhost:8080/analytics`;

  constructor(
    private http: HttpClient
  ) {
  }

  getUserRoleStats() {
    return this.http.get<number[]>(this.baseUrl + '/UserRoleCount');
  }

  getSocialGroupCount() {
    return this.http.get<number[]>(this.baseUrl + '/SocialGroupCount');
  }
}
