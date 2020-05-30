import {Component, OnInit} from '@angular/core';
import {Page} from '../../model/page';
import {Select, Store} from '@ngxs/store';
import {forkJoin, Observable} from 'rxjs';
import {map, mergeMap} from 'rxjs/operators';
import {PageState} from '../../store/page-store/page.state';

@Component({
  selector: 'pages-tab',
  templateUrl: './pages-tab.component.html',
  styleUrls: ['./pages-tab.component.scss']
})
export class PagesTabComponent implements OnInit {

  @Select(PageState.getUserPageSubs) subbedPages: Observable<Page[]>;

  constructor(
  ) {
  }

  ngOnInit() {
  }


}
