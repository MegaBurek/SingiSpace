import { Injectable } from '@angular/core';
import { Page } from '../model/page';
import { PAGES } from '../../assets/mock-data/mock-pages';

@Injectable({
  providedIn: 'root'
})
export class PagesService {

  constructor() { }

  getPages(): Page[]{
    return PAGES;
  }
}
