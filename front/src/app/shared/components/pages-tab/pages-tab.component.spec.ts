import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PagesTabComponent } from './pages-tab.component';

describe('PagesTabComponent', () => {
  let component: PagesTabComponent;
  let fixture: ComponentFixture<PagesTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PagesTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PagesTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
