import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient, HttpEvent, HttpEventType, HttpRequest, HttpResponse} from '@angular/common/http';

const url = 'http://localhost:8080/upload';

@Injectable({
  providedIn: 'root'
})
export class ImgService {

  private profilePhotoUrl = 'http://localhost:8080/images/uploadProfile';
  private postPhotoUrl = 'http://localhost:8080/images/posts';
  private returnImageDownloadUrl = 'http://localhost:8080/images/fileDownloadUrl';

  constructor(
    private http: HttpClient
  ) {
  }

  uploadProfile(file) {
    const formData: FormData = new FormData();

    formData.append('file', file);

    return this.http.post(`${this.profilePhotoUrl}`, formData, {responseType: 'text'});
  }

  getFiles() {
    return this.http.get(`${this.profilePhotoUrl}`);
  }
}
