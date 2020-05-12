import { Injectable } from '@angular/core';
import { GROUPS } from '../../../assets/mock-data/mock-groups';
import { Group } from 'src/app/model/group';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor() { }

  getGroups(): Group[] {
    return GROUPS;
  }

}
