import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Theme} from '../../model/theme';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {ThemeState} from '../../store/themes-store/theme.state';

@Component({
  selector: 'app-theme-detail',
  templateUrl: './theme-detail.component.html',
  styleUrls: ['./theme-detail.component.scss']
})
export class ThemeDetailComponent implements OnInit {

  @Select(ThemeState.selectTheme) theme: Observable<Theme>;

  constructor(
    private activatedRoute: ActivatedRoute,
    private store: Store
  ) { }

  ngOnInit() {
  }

}
