import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Store } from '@ngxs/store';
import { RegisterUser } from 'src/app/store/user-store/user.actions';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NotificaitionService } from 'src/app/services/notificaition.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  rePassword = '';

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
    provider: 'local',
    permission: {
      id: null,
      authority: 'ROLE_LEARNER'
    }
  };

  constructor(
    private notify: NotificaitionService,
    private store: Store,
    private router: Router
  ) {
  }

  ngOnInit() {
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
      this.store.dispatch(new RegisterUser(this.user))
        .subscribe(_ =>
          this.notify.showSuccess('You have succesfully registered!', 'Notification')
        );
      this.router.navigate(['/login']);
    }
  }

  validateEmail(email) {
    // tslint:disable-next-line:max-line-length
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

}
