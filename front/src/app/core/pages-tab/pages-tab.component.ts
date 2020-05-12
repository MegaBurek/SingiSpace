import { Component, OnInit } from '@angular/core';
import { PagesService } from '../../services/pages/pages.service';
import { Page } from '../../model/page';

@Component({
  selector: 'pages-tab',
  templateUrl: './pages-tab.component.html',
  styleUrls: ['./pages-tab.component.scss']
})
export class PagesTabComponent implements OnInit {

  pages: Page[];

  constructor(
    private pageService: PagesService
  ) { }

  ngOnInit() {
    this.getPages();
  }

  getPages(): void{
    this.pages = this.pageService.getPages();
  }

}
