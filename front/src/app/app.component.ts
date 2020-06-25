import {Component, OnInit, OnDestroy, AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthService} from './services/auth/auth.service';
import {ModalService} from './_modal';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy, AfterViewInit {
  title = 'SingiSpace';

  constructor(
    private loginService: AuthService,
    private router: Router,
    private modal: ModalService
  ) {
  }

  getUsername() {
    const username = this.loginService.getLoggedInUsername();
    return username;
  }

  onLogout() {
    this.loginService.logout();
  }

  isLoggedIn() {
    return this.loginService.isLoggedIn();
  }

  isAdmin() {
    return this.loginService.isAdminLogged();
  }

  toHome() {
    this.router.navigate(['/home']);
  }

  toDashboard() {
    this.router.navigate(['/dashboard']);
  }

  toDiscovery() {
    this.router.navigate(['/discovery']);
  }

  ngOnInit() {
    this.isLoggedIn();
    this.getUsername();
    this.isAdmin();
  }

  ngOnDestroy() {
    localStorage.clear();
  }

  ngAfterViewInit() {
    this.isLoggedIn();
    this.getUsername();
    this.isAdmin();
  }

  openModal(id) {
    this.modal.open(id);
  }
}
