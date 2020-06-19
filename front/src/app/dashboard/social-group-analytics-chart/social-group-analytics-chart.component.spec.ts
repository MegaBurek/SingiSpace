import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialGroupAnalyticsChartComponent } from './social-group-analytics-chart.component';

describe('SocialGroupAnalyticsChartComponent', () => {
  let component: SocialGroupAnalyticsChartComponent;
  let fixture: ComponentFixture<SocialGroupAnalyticsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SocialGroupAnalyticsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialGroupAnalyticsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
