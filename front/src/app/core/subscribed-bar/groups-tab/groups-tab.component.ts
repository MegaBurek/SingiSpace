import { Component, OnInit } from '@angular/core';

import { GroupsService } from '../../../services/groups.service'
import { Group } from '../../../model/group';

@Component({
  selector: 'app-groups-tab',
  templateUrl: './groups-tab.component.html',
  styleUrls: ['./groups-tab.component.scss']
})


export class GroupsTabComponent implements OnInit {

  groups: Group[];

  constructor(
    private groupService: GroupsService
  ) { }

  ngOnInit() {
    this.getGroups();
  }

  getGroups(): void{
    this.groups = this.groupService.getGroups();
  }

}
