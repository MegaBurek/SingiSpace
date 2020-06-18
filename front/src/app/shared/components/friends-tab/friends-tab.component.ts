import { Component, OnInit } from '@angular/core';
import { Friend } from 'src/app/model/friend';
import { FriendsService } from 'src/app/services/friends/friends.service';
import {UserState} from '../../../store/user-store/user.state';
import {Observable} from 'rxjs';
import {Select} from '@ngxs/store';

@Component({
  selector: 'friends-tab',
  templateUrl: './friends-tab.component.html',
  styleUrls: ['./friends-tab.component.scss']
})
export class FriendsTabComponent implements OnInit {

  @Select(UserState.getMyFriends) userFriends: Observable<Friend[]>;
  constructor(
    private friendService: FriendsService
  ) { }

  ngOnInit() {
  }


}
