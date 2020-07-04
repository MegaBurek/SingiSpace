import {Component, OnInit} from '@angular/core';
import {Page} from '../../../model/page';
import {NotificaitionService} from '../../../services/notificaition.service';
import {Store} from '@ngxs/store';
import {Router} from '@angular/router';
import {CreatePage} from '../../../store/user-store/page.actions';
import {AuthService} from '../../../services/auth/auth.service';
import {error} from 'util';
import {ModalService} from '../../../_modal';
import {ImgService} from '../../../services/img.service';
import {PagesService} from '../../../services/pages/pages.service';
import {CreateTheme} from '../../../store/user-store/theme.action';

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
    imgUrl: '',
    categories: [],
    members: [],
    feed: []
  };

  public imagePath;
  imageSrc: any;

  selectedFile: File;

  userSelects = [];
  categories = [{id: '001', name: 'Briefing'}, {id: '002', name: 'Exam'}, {id: '003', name: 'Test'}, {id: '004', name: 'Trip'},
    {id: '005', name: 'Sport'}, {id: '006', name: 'Event'}];

  constructor(
    private notify: NotificaitionService,
    private store: Store,
    private router: Router,
    private authService: AuthService,
    private modal: ModalService,
    private imgService: ImgService,
    private pagesService: PagesService
  ) {
  }

  ngOnInit() {
  }

  createPage() {
    if (this.page.name === '') {
      this.notify.showError('Please enter a name', 'Notification');
    } else if (this.page.name.length < 6) {
      this.notify.showError('The name must be more than 6 characters', 'Notification');
    } else if (this.userSelects.length === 0) {
      this.notify.showError('You must select at least one category', 'Notification');
    } else if (this.page.desc === '') {
      this.notify.showError('Please enter a description', 'Notification');
    } else if (this.page.desc.length < 10) {
      this.notify.showError('Write a longer description', 'Notification');
    } else if (this.imageSrc === null) {
      this.notify.showError('Please upload an image', 'Notification');
    } else {
      this.page.owner = this.authService.getCurrentUserID();
      for (let i = 0; i < this.userSelects.length; i++) {
        this.page.categories.push(this.userSelects[i].name);
      }
      const selectedFileName = this.selectedFile.name;
      const uniqueName = this.makeid(10) + selectedFileName;
      const blob = this.selectedFile.slice(0, this.selectedFile.size);
      const newFile = new File([blob], uniqueName);
      this.imgService.uploadPagePhoto(newFile).subscribe(
        imgUrl => {
          this.page.imgUrl = imgUrl.toString();
          this.store.dispatch(new CreatePage(this.page));
          this.notify.showSuccess('Successfully created page', 'Notification');
          this.router.navigate(['/my-pages']);
        }, error1 => {
          console.log(error1);
        }
      );
      this.selectedFile = undefined;
      this.router.navigate(['/home']);
    }
  }

  makeid(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
  }

  isSelected(s) {
    return this.userSelects.findIndex((item) => item.id === s.id) > -1 ? true : false;
  }

  selectSuggestion(c) {
    this.userSelects.find((item) => item.id === c.id) ?
      this.userSelects = this.userSelects.filter((item) => item.id !== c.id) :
      this.userSelects.push(c);
  }

  openPageCompletion(id) {
    this.modal.open(id);
  }

  checkIfEmpty() {
    if (this.page.name === '' || this.userSelects.length === 0 || this.page.desc === '') {
      return false;
    } else {
      return true;
    }
  }

  preview(files) {
    if (files.length === 0) {
      return;
    }

    const mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.notify.showError('Only images are supported', 'Notification');
      return;
    }

    this.selectedFile = files[0];

    const reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (event) => {
      this.imageSrc = reader.result;
    };
  }

  checkIfCompletionEmpty() {
    if (this.imageSrc === null) {
      return false;
    } else {
      return true;
    }
  }

}
