import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/auth/login.service'
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  attempt:Boolean = false;

  constructor(
    private loginService: LoginService,
    private router: Router

  ) { }

  login(form: NgForm) {
    if (form.invalid) {
      this.attempt = true;
    }
    if(this.loginService.login(form.value.username, form.value.password)){
      this.router.navigate(['/home']);
    }
    else{
      this.attempt = true;
    };
  }

  logout() {
    this.loginService.logout();
  }


}
