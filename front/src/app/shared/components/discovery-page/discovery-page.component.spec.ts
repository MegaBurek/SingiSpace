import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscoveryPageComponent } from './discovery-page.component';

describe('DiscoveryPageComponent', () => {
  let component: DiscoveryPageComponent;
  let fixture: ComponentFixture<DiscoveryPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscoveryPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscoveryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
