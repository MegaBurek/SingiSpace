import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlaggedContentComponent } from './flagged-content.component';

describe('FlaggedContentComponent', () => {
  let component: FlaggedContentComponent;
  let fixture: ComponentFixture<FlaggedContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlaggedContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlaggedContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
