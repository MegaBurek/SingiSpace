import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {User} from '../../model/user';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAccService {

  private userUrl = 'http://localhost:8080/user';
  private userAccUrl = `http://localhost:8080/userAcc`;

  constructor(
    private http: HttpClient
  ) {
  }

  getUserByUsername(username) {
    return this.http.get<User>(this.userUrl + `/${username}`);
  }

  getCurrentUser(id: string) {
    return this.http.get<User>(this.userAccUrl + `/${id}`);
  }

  registerTutor(tutor) {
    return this.http.post<User>(this.userAccUrl + `/register/tutor`, tutor);
  }

  registerAdmin(admin) {
    return this.http.post<User>(this.userAccUrl + `/register/tutor`, admin);
  }

  registerLearner(learner) {
    return this.http.post<User>(this.userAccUrl + `/register/tutor`, learner);
  }

  // editUser(id, user) {
  //   return this.http.put<User>(this.baseUrl + `/${id}`, user);
  // }
  //
  // removeUser(id) {
  //   return this.http.delete<string>(this.baseUrl + `/${id}`);
  // }

  // errorMgmt(error: HttpErrorResponse) {
  //   let errorMessage = '';
  //   if (error.error instanceof ErrorEvent) {
  //     // Get client-side error
  //     errorMessage = error.error.message;
  //   } else {
  //     // Get server-side error
  //     errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  //   }
  //   console.log(errorMessage);
  //   return throwError(errorMessage);
  // }
}
