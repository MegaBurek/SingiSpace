import {Component, OnInit} from '@angular/core';
import {Theme} from 'src/app/model/theme';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {GetTheme, SelectTheme} from '../../../store/user-store/theme.action';
import {Router} from '@angular/router';
import {UserState} from '../../../store/user-store/user.state';

@Component({
  selector: 'themes-tab',
  templateUrl: './themes-tab.component.html',
  styleUrls: ['./themes-tab.component.scss']
})
export class ThemesTabComponent implements OnInit {

  @Select(UserState.getUserThemeSubs) subbedThemes: Observable<Theme[]>;

  constructor(
    private store: Store,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  openTheme(theme) {
    this.store.dispatch(new SelectTheme(theme));
    this.router.navigate(['/theme/' + theme.name]);
  }

}
