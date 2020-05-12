import { Injectable } from '@angular/core';
import { Theme } from 'src/app/model/theme';
import { THEMES } from 'src/assets/mock-data/mock-themes';

@Injectable({
  providedIn: 'root'
})
export class ThemesService {

  constructor() { }

  getThemes(): Theme[] {
    return THEMES;
  }
}
