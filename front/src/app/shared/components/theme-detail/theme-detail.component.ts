import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Theme} from '../../../model/theme';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {ModalService} from '../../../_modal';
import {Post} from '../../../model/post';
import {NotificaitionService} from '../../../services/notificaition.service';
import {CreatePost, UpdateTheme} from '../../../store/user-store/theme.action';
import {error} from 'util';
import {PostsService} from '../../../services/posts/posts.service';
import {UserState} from '../../../store/user-store/user.state';
import {ImgService} from '../../../services/img.service';

@Component({
  selector: 'app-theme-detail',
  templateUrl: './theme-detail.component.html',
  styleUrls: ['./theme-detail.component.scss']
})
export class ThemeDetailComponent implements OnInit {

  @Select(UserState.getSelectedTheme) selectedTheme: Observable<Theme>;
  @Select(UserState.getSelectedThemeFeed) feed: Observable<Post[]>;
  currentTheme: Theme;

  public imagePath;
  imageSrc: any;
  selectedFile: File;

  textPost = false;
  imagePost = false;
  createPostopen = false;

  post: Post = {
    title: '',
    owner: '',
    textContent: '',
    imgContent: '',
    comments: [],
    likes: []
  };

  constructor(
    private activatedRoute: ActivatedRoute,
    private store: Store,
    private modalService: ModalService,
    private notify: NotificaitionService,
    private postsService: PostsService,
    private imgService: ImgService
  ) {
    this.selectedTheme.subscribe((value) => {
      this.currentTheme = value;
    });
  }

  ngOnInit() {
    // this.store.dispatch(new GetSelectedThemeFeed());
  }

  tryCreatePost() {
    if (this.post.title === '') {
      this.notify.showError('Please enter a title', 'Notification');
    } else if (this.post.title.length <= 4) {
      this.notify.showError('Please enter at least 4 characters', 'Notification');
    } else if (this.post.textContent === '') {
      this.notify.showError('Please enter some text content', 'Notification');
    } else if (this.post.textContent.length <= 10) {
      this.notify.showError('Please enter at least 10 characters', 'Notification');
    } else {
      if (this.textPost === true) {
        this.currentTheme.feed.push(this.post);
        this.postsService.createThemePost(this.currentTheme.id, this.post)
          .subscribe((theme) => {
            this.store.dispatch(new UpdateTheme(theme.id, theme))
              .subscribe(_ => {
                this.closeCreatePage('custom-modal-1');
                this.textPost = false;
                this.createPostopen = false;
                this.notify.showSuccess('You have successfully created a post', 'Notification');
              }, error1 => {
                console.error(error1);
              });
          }, error2 => {
            console.error(error2);
          });
      } else {
        this.imgService.uploadPostPhoto(this.selectedFile).subscribe((downloadUrl) => {
          this.post.imgContent = downloadUrl;
          this.currentTheme.feed.push(this.post);
          console.log(this.post);
          this.postsService.createThemePost(this.currentTheme.id, this.post).subscribe((updatedTheme) => {
            this.store.dispatch(new UpdateTheme(updatedTheme.id, updatedTheme));
            this.closeCreatePage('custom-modal-1');
            this.textPost = false;
            this.createPostopen = false;
            this.notify.showSuccess('You have successfully created a post', 'Notification');
          }, error1 => {
            console.error(error1);
          });
        }, error2 => {
          console.error(error2);
        });
      }

    }
  }

  openCreatePage(id: string) {
    this.modalService.open(id);
  }

  closeCreatePage(id: string) {
    this.modalService.close(id);
  }

  openPost(id: string) {
    this.modalService.open(id);
  }

  closePost(id: string) {
    this.modalService.close(id);
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

  openCreateTextPost() {
    this.textPost = true;
    this.createPostopen = true;
  }

  openCreateImagePost() {
    this.imagePost = true;
    this.createPostopen = true;
  }


}
