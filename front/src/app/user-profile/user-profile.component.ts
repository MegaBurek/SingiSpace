import { Component, OnInit } from '@angular/core';
import {AuthService} from '../services/auth/auth.service';
import {User} from '../model/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserAccService} from '../services/users/user-acc.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: any = {};
  imageUrl = '';
  edit = false;
  editForm: FormGroup;

  constructor(
    private logInService: AuthService,
    private formBuilder: FormBuilder,
    private userAccService: UserAccService
  ) { this.createEditForm(); }

  ngOnInit() {
    this.userAccService.getUserByID(this.logInService.getCurrentUserID()).subscribe(res => {
      this.user = res;
      console.log(this.user);
    });
  }

  getUsername() {
    const username = this.logInService.getLoggedInUsername();
    return username;
  }

  createEditForm() {
    this.editForm = this.formBuilder.group({
      displayName: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      dob: ['', Validators.required]
    });
  }

}
