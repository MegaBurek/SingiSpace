import { Component, OnInit } from '@angular/core';
import { Group } from 'src/app/model/group';
import { GroupsService } from 'src/app/services/groups/groups.service';

@Component({
  selector: 'groups-tab',
  templateUrl: './groups-tab.component.html',
  styleUrls: ['./groups-tab.component.scss']
})
export class GroupsTabComponent implements OnInit {

  groups: Group[] = [];

  constructor(
    private groupService: GroupsService
  ) { }

  ngOnInit() {
    this.getGroups();
  }

  getGroups():void{
    this.groups = this.groupService.getGroups();
  }

}
