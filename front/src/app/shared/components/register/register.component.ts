import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/model/user';
import {Store} from '@ngxs/store';
import {RegisterUser} from 'src/app/store/user-store/user.actions';
import {Router} from '@angular/router';
import {NotificaitionService} from 'src/app/services/notificaition.service';
import {ModalService} from '../../../_modal';
import {Observable} from 'rxjs';
import {ImgService} from '../../../services/img.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  rePassword = '';
  public imagePath;
  imageSrc: any;

  selectedFile: File;
  progress = 0;

  user: User = {
    id: null,
    name: '',
    surname: '',
    username: '',
    password: '',
    email: '',
    dob: null,
    imgUrl: '',
    page_subs: [],
    theme_subs: [],
    friends: [],
    provider: 'local',
    permission: {
      id: null,
      authority: 'ROLE_LEARNER'
    }
  };

  constructor(
    private notify: NotificaitionService,
    private store: Store,
    private router: Router,
    private modal: ModalService,
    private imgService: ImgService
  ) {
  }

  ngOnInit() {
  }

  openAccountCompletion(id) {
    this.modal.open(id);
  }

  checkIfEmpty() {
    if (this.user.name === '' || this.user.surname === '' || this.user.email === '' || this.user.dob === null) {
      return false;
    } else {
      return true;
    }
  }

  checkIfCompletionEmpty() {
    if (this.user.username === '' || this.user.password === '' || this.rePassword === '') {
      return false;
    } else {
      return true;
    }
  }

  tryRegister() {
    if (this.user.name === '') {
      this.notify.showError('Please enter a name', 'Notification');
    } else if (this.user.surname === '') {
      this.notify.showError('Please enter a surname', 'Notification');
    } else if (this.user.email === '') {
      this.notify.showError('Please enter your email', 'Notification');
    } else if (!this.validateEmail(this.user.email)) {
      this.notify.showError('Your email is not valid', 'Notification');
    } else if (this.user.dob === null) {
      this.notify.showError('Please enter your date of birth', 'Notification');
    } else if (this.user.username === '') {
      this.notify.showError('Please enter a username', 'Notification');
    } else if (this.user.password === '') {
      this.notify.showError('Please enter a password', 'Notification');
    } else if (this.rePassword === '') {
      this.notify.showError('Please re-enter your password', 'Notification');
    } else if (this.user.password !== this.rePassword) {
      this.notify.showError('Your passwords do not match', 'Notification');
    } else if (this.user.password.length < 6) {
      this.notify.showError('Your password has less than 6 characters', 'Notification');
    } else {
      this.imgService.uploadProfile(this.selectedFile).subscribe(
        imgUrl => {
          this.user.imgUrl = imgUrl.toString();
          this.store.dispatch(new RegisterUser(this.user));
          this.notify.showSuccess('You have successfully registered', 'Notification');
        }, error => {
          console.log(error);
          this.progress = 0;
          this.selectedFile = undefined;
        }
      );
      this.selectedFile = undefined;
      this.router.navigate(['/login']);
    }
  }

  validateEmail(email) {
    // tslint:disable-next-line:max-line-length
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

  preview(files) {
    if (files.length === 0) {
      return;
    }

    const mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.notify.showError('Only images are supported', 'Notifcation');
      return;
    }

    this.selectedFile = files[0];

    const reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (event) => {
      this.imageSrc = reader.result;
    };
  }


}
