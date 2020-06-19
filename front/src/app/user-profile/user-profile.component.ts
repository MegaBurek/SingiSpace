import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth/auth.service';
import {User} from '../model/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {UserState} from '../store/user-store/user.state';
import {Select, Store} from '@ngxs/store';
import {ActivatedRoute} from '@angular/router';
import {GetUserViewByUsername} from '../store/user-store/user.actions';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  @Select(UserState.getSelectedUser) selectedUser: Observable<User>;
  editForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService,
    private store: Store
  ) {
    const username = this.activatedRoute.snapshot.params.username;
    this.store.dispatch(new GetUserViewByUsername(username));

    this.createEditForm();
  }

  ngOnInit() {
  }

  getCurrentUserID() {
    return this.authService.getCurrentUserID();
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
