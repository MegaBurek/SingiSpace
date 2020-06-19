import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {Store} from '@ngxs/store';
import {GetSocialGroupCount, GetUserRoleCount} from '../store/analytics-store/analytics.actions';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(
    private router: Router,
    private store: Store
  ) {
  }

  ngOnInit() {
    this.store.dispatch(new GetUserRoleCount());
    this.store.dispatch(new GetSocialGroupCount());
  }

  toCreateTutor() {
    this.router.navigate(['/create-tutor']);
  }

  toCreateAdmin() {
    this.router.navigate(['/create-admin']);
  }

  toManageUsers() {
    this.router.navigate(['/manage-users']);
  }

  toFlaggedContent() {
    this.router.navigate(['/flagged-content']);
  }
}
