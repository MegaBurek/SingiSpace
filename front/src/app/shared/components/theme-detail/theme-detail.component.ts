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

@Component({
  selector: 'app-theme-detail',
  templateUrl: './theme-detail.component.html',
  styleUrls: ['./theme-detail.component.scss']
})
export class ThemeDetailComponent implements OnInit {

  @Select(UserState.getSelectedTheme) selectedTheme: Observable<Theme>;
  @Select(UserState.getSelectedThemeFeed) feed: Observable<Post[]>;
  currentTheme: Theme;

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
  ) {
  }

  ngOnInit() {
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
      this.selectedTheme.subscribe(x => {
        this.currentTheme = x;
      });
      this.currentTheme.feed.push(this.post);
      this.postsService.createThemePost(this.currentTheme.id, this.post)
        .subscribe(theme => {
          this.store.dispatch(new UpdateTheme(theme.id, theme))
            .subscribe(_ => {
              this.closeCreatePage('custom-modal-1');
              this.notify.showSuccess('You have succesfully created a post', 'Notification');
            });
          error(e => {
            console.log('Error: ' + e);
          });
        });
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

}
