import {Component, OnInit} from '@angular/core';
import {Page} from '../../model/page';
import { Store } from '@ngxs/store';
import {Observable} from 'rxjs';

@Component({
  selector: 'pages-tab',
  templateUrl: './pages-tab.component.html',
  styleUrls: ['./pages-tab.component.scss']
})
export class PagesTabComponent implements OnInit {

  subbedPages: Observable<Page[]>;

  constructor(
    private store: Store
  ) {
  }

  ngOnInit() {
    this.getSubbedPages();

  }

  getSubbedPages() {
    this.subbedPages = this.store.select(state => state.subbedPages);
  }

}
