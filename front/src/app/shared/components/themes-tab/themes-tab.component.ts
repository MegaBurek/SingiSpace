import {Component, OnInit} from '@angular/core';
import {Theme} from 'src/app/model/theme';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {UserState} from '../../../store/user-store/user.state';

@Component({
  selector: 'themes-tab',
  templateUrl: './themes-tab.component.html',
  styleUrls: ['./themes-tab.component.scss']
})
export class ThemesTabComponent implements OnInit {

  @Select(UserState.getUserThemeSubs) subbedThemes: Observable<Theme[]>;

  constructor(
  ) {
  }

  ngOnInit() {
  }

}
