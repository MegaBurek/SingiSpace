import {Component, OnInit} from '@angular/core';
import {UserState} from '../../../store/user-store/user.state';
import {Select} from '@ngxs/store';
import {Page} from '../../../model/page';
import {Observable} from 'rxjs';

@Component({
  selector: 'pages-tab',
  templateUrl: './pages-tab.component.html',
  styleUrls: ['./pages-tab.component.scss']
})
export class PagesTabComponent implements OnInit {

  @Select(UserState.getUserPageSubs) subbedPages: Observable<Page[]>;

  constructor(
  ) {
  }

  ngOnInit() {
  }


}
