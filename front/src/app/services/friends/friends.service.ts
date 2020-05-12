import { Injectable } from '@angular/core';
import { FRIENDS } from 'src/assets/mock-data/mock-friends';
import { Friend } from 'src/app/model/friend';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor() { }

  getFriends(): Friend[]{
    return FRIENDS;
  }
}
