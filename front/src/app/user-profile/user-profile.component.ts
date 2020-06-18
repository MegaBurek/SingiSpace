import { Component, OnInit } from '@angular/core';
import {AuthService} from '../services/auth/auth.service';
import {User} from '../model/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserAccService} from '../services/users/user-acc.service';
import {Observable} from 'rxjs';
import {UserState} from '../store/user-store/user.state';
import {Select} from '@ngxs/store';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  @Select(UserState.getSelectedUser) selectedUser: Observable<User>;
  editForm: FormGroup;

  constructor(
    private logInService: AuthService,
    private formBuilder: FormBuilder
  ) { this.createEditForm(); }

  ngOnInit() {
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
