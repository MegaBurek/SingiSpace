import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewUserAnalyticsChartComponent } from './new-user-analytics-chart.component';

describe('NewUserAnalyticsChartComponent', () => {
  let component: NewUserAnalyticsChartComponent;
  let fixture: ComponentFixture<NewUserAnalyticsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewUserAnalyticsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserAnalyticsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
