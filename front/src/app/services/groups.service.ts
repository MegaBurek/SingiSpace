import { Injectable } from '@angular/core';
import { Group } from '../model/group';
import { GROUPS } from '../../assets/mock-data/mock-groups';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor() { }

  getGroups(): Group[] {
    return GROUPS;
  }

}
