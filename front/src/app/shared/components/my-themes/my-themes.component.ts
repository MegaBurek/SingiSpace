import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Theme} from '../../../model/theme';
import {AuthService} from '../../../services/auth/auth.service';
import {ThemesService} from '../../../services/themes/themes.service';
import {Router} from '@angular/router';
import {UserState} from '../../../store/user-store/user.state';
import {Select, Store} from '@ngxs/store';

@Component({
  selector: 'app-my-themes',
  templateUrl: './my-themes.component.html',
  styleUrls: ['./my-themes.component.scss']
})
export class MyThemesComponent implements OnInit {

  @Select(UserState.getUserOwnedThemes) ownedThemes: Observable<Theme[]>;

  constructor(
    private store: Store,
    private router: Router
  ) {
  }

  ngOnInit() {

  }

  toTheme(name) {
    this.router.navigate(['/theme/' + name]);
  }

}
