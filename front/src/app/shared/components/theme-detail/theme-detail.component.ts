import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Theme} from '../../../model/theme';
import {Store} from '@ngxs/store';
import {ModalService} from '../../../_modal';
import {Post} from '../../../model/post';
import {NotificaitionService} from '../../../services/notificaition.service';
import {PostsService} from '../../../services/posts/posts.service';
import {ImgService} from '../../../services/img.service';
import {ThemesService} from '../../../services/themes/themes.service';
import {Observable} from 'rxjs';
import {AuthService} from '../../../services/auth/auth.service';
import {Friend} from '../../../model/friend';
import {FriendsService} from '../../../services/friends/friends.service';

@Component({
  selector: 'app-theme-detail',
  templateUrl: './theme-detail.component.html',
  styleUrls: ['./theme-detail.component.scss']
})
export class ThemeDetailComponent implements OnInit {

  selectedTheme: Observable<Theme>;
  selectedThemeFeed: Observable<Post[]>;
  owner: Friend;

  public imagePath;
  imageSrc: any;
  selectedFile: File;

  textPost = false;
  imagePost = false;
  createPostopen = false;

  post: Post = {
    id: null,
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
    private imgService: ImgService,
    private themesService: ThemesService,
    private authService: AuthService,
    private friendsService: FriendsService,
    private router: Router
  ) {
    const name = this.activatedRoute.snapshot.params.themeName;
    this.selectedTheme = this.themesService.getThemeByName(name);
    this.selectedThemeFeed = this.themesService.getThemeFeed(name);
  }

  ngOnInit() {
    this.selectedTheme.subscribe((theme) => {
      this.friendsService.getFriend(theme.owner).subscribe((owner) => {
        this.owner = owner;
      });
    });
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
      this.post.owner = this.authService.getCurrentUserID();
      if (this.textPost === true) {
        this.selectedTheme.subscribe((theme) => {
          this.postsService.createThemePost(theme.id, this.post).subscribe(_ => {
            this.selectedThemeFeed = this.themesService.getThemeFeed(theme.name);
            this.closeCreatePage('custom-modal-1');
            this.textPost = false;
            this.createPostopen = false;
            this.notify.showSuccess('You have successfully created a post', 'Notification');
          }, error1 => {
            console.error(error1);
          });
        });
      } else {
        const selectedFileName = this.selectedFile.name;
        const uniqueName = this.makeid(10) + selectedFileName;
        const blob = this.selectedFile.slice(0, this.selectedFile.size);
        const newFile = new File([blob], uniqueName);
        this.imgService.uploadPostPhoto(newFile).subscribe((downloadUrl) => {
          this.post.imgContent = downloadUrl;
          this.selectedTheme.subscribe((theme) => {
            this.postsService.createThemePost(theme.id, this.post).subscribe(_ => {
              this.selectedThemeFeed = this.themesService.getThemeFeed(theme.name);
              this.closeCreatePage('custom-modal-1');
              this.imagePost = false;
              this.createPostopen = false;
              this.notify.showSuccess('You have successfully created a post', 'Notification');
            }, error1 => {
              console.error(error1);
            });
          });
        }, error2 => {
          console.error(error2);
        });
      }

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

  toUser(name) {
    this.router.navigate(['/user/' + name]);
  }


}
