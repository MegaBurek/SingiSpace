import {Component, OnInit} from '@angular/core';
import {Page} from '../../../model/page';
import {NotificaitionService} from '../../../services/notificaition.service';
import {Store} from '@ngxs/store';
import {Router} from '@angular/router';
import {CreatePage} from '../../../store/user-store/page.actions';
import {AuthService} from '../../../services/auth/auth.service';
import {error} from 'util';

@Component({
  selector: 'app-page-creation',
  templateUrl: './page-creation.component.html',
  styleUrls: ['./page-creation.component.scss']
})
export class PageCreationComponent implements OnInit {

  page: Page = {
    id: null,
    name: '',
    desc: '',
    owner: '',
    categories: [],
    members: [],
    feed: []
  };

  userSelects = [];
  categories = [{id: '001', name: 'Information'}, {id: '002', name: 'Exams'}, {id: '003', name: 'Tests'}, {
    id: '004',
    name: 'Trips'
  }];

  constructor(
    private notify: NotificaitionService,
    private store: Store,
    private router: Router,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
  }

  createPage() {
    if (this.page.name === '') {
      this.notify.showError('Please enter a name', 'Notification');
    } else if (this.page.name.length < 6) {
      this.notify.showError('The name must be more than 6 characters', 'Notification');
    } else if (this.page.desc === '') {
      this.notify.showError('Please enter a description', 'Notification');
    } else if (this.page.desc.length < 10) {
      this.notify.showError('Write a longer description', 'Notification');
    } else {
      this.page.owner = this.authService.getCurrentUserID();
      for(let i = 0; i < this.userSelects.length; i++) {
        this.page.categories.push(this.userSelects[i].name);
      }
      this.store.dispatch(new CreatePage(this.page))
        .subscribe(_ =>
          this.notify.showSuccess('You have successfully created a page', 'Notification')
        );
      this.router.navigate(['/home']);
    }
  }

  isSelected(s) {
    return this.userSelects.findIndex((item) => item.id === s.id) > -1 ? true : false;
  }

  selectSuggestion(c) {
    this.userSelects.find((item) => item.id === c.id) ?
      this.userSelects = this.userSelects.filter((item) => item.id !== c.id) :
      this.userSelects.push(c);
  }

}
