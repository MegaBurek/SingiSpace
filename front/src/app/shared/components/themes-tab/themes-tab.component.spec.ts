import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThemesTabComponent } from './themes-tab.component';

describe('ThemesTabComponent', () => {
  let component: ThemesTabComponent;
  let fixture: ComponentFixture<ThemesTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThemesTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThemesTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
