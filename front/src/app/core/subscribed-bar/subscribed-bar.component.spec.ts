import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedBarComponent } from './subscribed-bar.component';

describe('SubscribedBarComponent', () => {
  let component: SubscribedBarComponent;
  let fixture: ComponentFixture<SubscribedBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscribedBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribedBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
