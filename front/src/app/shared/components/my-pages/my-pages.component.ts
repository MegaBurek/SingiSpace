import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Page} from '../../../model/page';
import {AuthService} from '../../../services/auth/auth.service';
import {Router} from '@angular/router';
import {PagesService} from '../../../services/pages/pages.service';
import {UserState} from '../../../store/user-store/user.state';
import {Select, Store} from '@ngxs/store';

@Component({
  selector: 'app-my-pages',
  templateUrl: './my-pages.component.html',
  styleUrls: ['./my-pages.component.scss']
})
export class MyPagesComponent implements OnInit {

  @Select(UserState.getUserOwnedPages) ownedPages: Observable<Page[]>;

  constructor(
    private store: Store,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  toPage(name) {
    this.router.navigate(['/page/' + name]);
  }

}
