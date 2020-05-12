import { Component, OnInit } from '@angular/core';
import { Friend } from 'src/app/model/friend';
import { FriendsService } from 'src/app/services/friends/friends.service';

@Component({
  selector: 'friends-tab',
  templateUrl: './friends-tab.component.html',
  styleUrls: ['./friends-tab.component.scss']
})
export class FriendsTabComponent implements OnInit {

  friends: Friend[] = [];

  constructor(
    private friendService: FriendsService
  ) { }

  ngOnInit() {
    this.getFriends();
  }

  getFriends():void{
    this.friends = this.friendService.getFriends();

  }

}
