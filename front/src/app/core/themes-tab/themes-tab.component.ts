import { Component, OnInit } from '@angular/core';
import { Theme } from 'src/app/model/theme';
import { ThemesService } from 'src/app/services/themes/themes.service';

@Component({
  selector: 'themes-tab',
  templateUrl: './themes-tab.component.html',
  styleUrls: ['./themes-tab.component.scss']
})
export class ThemesTabComponent implements OnInit {

  themes: Theme[];

  constructor(
    private themeService: ThemesService
  ) { }

  ngOnInit() {
    this.getThemes();
  }

  getThemes(): void{
    this.themes = this.themeService.getThemes();
  }

}
