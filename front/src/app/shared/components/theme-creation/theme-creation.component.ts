import {Component, OnInit} from '@angular/core';
import {Theme} from '../../../model/theme';
import {NotificaitionService} from '../../../services/notificaition.service';
import {Store} from '@ngxs/store';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth/auth.service';
import {CreateTheme} from '../../../store/themes-store/theme.action';

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
    members: [],
    feed: []
  };

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
      this.store.dispatch(new CreateTheme(this.theme))
        .subscribe(_ =>
          this.notify.showSuccess('You have succesfully created a theme', 'Notification')
        );
      this.router.navigate(['/home']);
    }
  }
}
