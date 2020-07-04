import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyThemesComponent } from './my-themes.component';

describe('MyThemesComponent', () => {
  let component: MyThemesComponent;
  let fixture: ComponentFixture<MyThemesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyThemesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyThemesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
