import {Component, OnInit} from '@angular/core';
import {AdminService} from '../../services/users/admin.service';
import {Friend} from '../../model/friend';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.scss']
})
export class ManageUsersComponent implements OnInit {

  tutors: Friend[];
  admins: Friend[];
  learners: Friend[];

  constructor(
    private adminService: AdminService
  ) {
    this.adminService.getAllAdmins().subscribe((value) => {
      this.admins = value;
    });
    this.adminService.getAllTutors().subscribe((value) => {
      this.tutors = value;
    });
    this.adminService.getAllLearners().subscribe((value) => {
      this.learners = value;
    });
  }

  ngOnInit() {
  }

}
