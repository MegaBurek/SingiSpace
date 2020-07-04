import {Component, OnInit} from '@angular/core';
import {ChartType, ChartOptions} from 'chart.js';
import {SingleDataSet, Label} from 'ng2-charts';
import {AnalyticsState} from '../../store/analytics-store/analytics.state';
import {Observable} from 'rxjs';
import {Select} from '@ngxs/store';

@Component({
  selector: 'social-group-analytics-chart',
  templateUrl: './social-group-analytics-chart.component.html',
  styleUrls: ['./social-group-analytics-chart.component.scss']
})
export class SocialGroupAnalyticsChartComponent implements OnInit {

  @Select(AnalyticsState.getallSocialGroups) pieChartData: Observable<number[]>;

  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false
    }
  };
  public pieChartLabels: Label[] = [['Pages'], ['Themes']];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

  constructor() {
  }

  ngOnInit() {
  }

}
