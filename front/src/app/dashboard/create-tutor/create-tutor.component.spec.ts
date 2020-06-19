import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTutorComponent } from './create-tutor.component';

describe('CreateTutorComponent', () => {
  let component: CreateTutorComponent;
  let fixture: ComponentFixture<CreateTutorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTutorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
