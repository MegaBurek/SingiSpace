import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Friend} from '../../model/friend';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private http: HttpClient
  ) {
  }

  private adminUrl = `http://localhost:8080/admin`;

  getAllLearners() {
    return this.http.get<Friend[]>(this.adminUrl + '/getLearners');
  }

  getAllAdmins() {
    return this.http.get<Friend[]>(this.adminUrl + '/getAdmins');
  }

  getAllTutors() {
    return this.http.get<Friend[]>(this.adminUrl + '/getTutors');
  }
}
