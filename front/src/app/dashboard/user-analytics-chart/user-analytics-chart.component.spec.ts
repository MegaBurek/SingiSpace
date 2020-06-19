import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAnalyticsChartComponent } from './user-analytics-chart.component';

describe('UserAnalyticsChartComponent', () => {
  let component: UserAnalyticsChartComponent;
  let fixture: ComponentFixture<UserAnalyticsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAnalyticsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAnalyticsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
