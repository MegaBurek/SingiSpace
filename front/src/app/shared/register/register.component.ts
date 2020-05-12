import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserAccService} from '../../services/users/user-acc.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  password = '';

  constructor(
    private formBuilder: FormBuilder,
    private userAccService: UserAccService,
    private toastr: ToastrService
  ) { this.createRegisterForm();
  }

  createRegisterForm() {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      repassword: ['', Validators.required],
      email: ['', Validators.required]
    });
  }
  ngOnInit() {
  }

  get f() {return this.registerForm.controls; }

  tryRegister() {
    this.password = this.f.password.value;
    if (this.f.name.value === '') {
      this.toastr.error('Please enter your name', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.surname.value === '') {
      this.toastr.error('Please enter your surname', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.username.value === '') {
      this.toastr.error('Please enter a username', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.password.value === '') {
      this.toastr.error('Please enter a password', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.repassword.value === '') {
      this.toastr.error('Please re-enter your password', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.password.value !== this.f.repassword.value) {
      this.toastr.error('Your passwords do not match', 'Notification', {
        timeOut: 1700
      });
    } else if (this.password.length < 6) {
      this.toastr.error('Your password must be more than 6 characters', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.email.value === '') {
      this.toastr.error('Please enter an email', 'Notification', {
        timeOut: 1700
      });
    } else if (!this.validateEmail(this.f.email.value)) {
      this.toastr.error('Your email is invalid', 'Notification', {
        timeOut: 1700
      });
    } else if (this.f.dob.value === '') {
      this.toastr.error('Please enter a date of birth', 'Notification', {
        timeOut: 1700
      });
    } else {

    }
  }

  validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

}
