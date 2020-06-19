import {Component, OnInit} from '@angular/core';
import {ChartType, ChartOptions} from 'chart.js';
import {SingleDataSet, Label} from 'ng2-charts';
import {AnalyticsState} from '../../store/analytics-store/analytics.state';
import {Observable} from 'rxjs';
import {Select} from '@ngxs/store';

@Component({
  selector: 'user-analytics-chart',
  templateUrl: './user-analytics-chart.component.html',
  styleUrls: ['./user-analytics-chart.component.scss']
})
export class UserAnalyticsChartComponent implements OnInit {

  @Select(AnalyticsState.getallUserRoles) pieChatData: Observable<number[]>;

  pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false
    }
  };
  pieChartLabels: Label[] = [['Admins'], ['Tutors'], ['Learners']];
  pieChartType: ChartType = 'pie';
  pieChartLegend = true;
  pieChartPlugins = [];

  constructor(
  ) {
  }

  ngOnInit() {
  }

}
