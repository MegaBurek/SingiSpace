import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../services/auth/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent{

  attempt : Boolean = false;

  constructor(
    private loginService: LoginService,
    private router: Router

  ) { }

  ngOnInit() {
  }

  login(form: NgForm) {
    if (form.invalid) {
      return;
    }
    if (this.loginService.login(form.value.username, form.value.password)) {
      return true;
    }else{
      this.attempt = true
    };
  }

  logout(){
    this.loginService.logout();
  }


}
