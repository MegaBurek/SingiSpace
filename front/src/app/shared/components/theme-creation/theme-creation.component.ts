import {Component, OnInit} from '@angular/core';
import {Theme} from '../../../model/theme';
import {NotificaitionService} from '../../../services/notificaition.service';
import {Store} from '@ngxs/store';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth/auth.service';
import {CreateTheme} from '../../../store/user-store/theme.action';

@Component({
  selector: 'app-theme-creation',
  templateUrl: './theme-creation.component.html',
  styleUrls: ['./theme-creation.component.scss']
})
export class ThemeCreationComponent implements OnInit {

  theme: Theme = {
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

  createTheme() {
    if (this.theme.name === '') {
      this.notify.showError('Please enter a name', 'Notification');
    } else if (this.theme.name.length < 6) {
      this.notify.showError('The name must be more than 6 characters', 'Notification');
    } else if (this.theme.desc === '') {
      this.notify.showError('Please enter a description', 'Notification');
    } else if (this.theme.desc.length < 10) {
      this.notify.showError('Write a longer description', 'Notification');
    } else {
      this.theme.owner = this.authService.getCurrentUserID();
      for(let i = 0; i < this.userSelects.length; i++) {
        this.theme.categories.push(this.userSelects[i].name);
      }
      this.store.dispatch(new CreateTheme(this.theme))
        .subscribe(_ =>
          this.notify.showSuccess('You have succesfully created a theme', 'Notification')
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
