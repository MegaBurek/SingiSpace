import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service'
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NotificaitionService } from 'src/app/services/notificaition.service';
import {PagesService} from '../../services/pages/pages.service';
import {Store} from '@ngxs/store';
import { GetUserPageSubs } from '../../store/page-store/page.actions';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: AuthService,
    private router: Router,
    private notify: NotificaitionService,
    private pagesService: PagesService,
    private store: Store

  ) {
    // redirect to home if already logged in
    // if (this.loginService.loggedInStatusChanged) {
    //   this.router.navigate(['/home'])
    // }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  async login() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      this.notify.showError('Enter username and password', 'Notificaiton');
      return;
    }
    this.loading = true;
    await this.loginService.login(this.f.username.value, this.f.password.value);
  }

  logout() {
    this.loginService.logout();
  }


}
