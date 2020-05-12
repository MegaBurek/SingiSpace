import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendsTabComponent } from './friends-tab.component';

describe('FriendsTabComponent', () => {
  let component: FriendsTabComponent;
  let fixture: ComponentFixture<FriendsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FriendsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
