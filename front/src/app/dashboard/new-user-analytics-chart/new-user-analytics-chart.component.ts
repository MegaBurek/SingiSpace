import { Component, OnInit } from '@angular/core';
import {ChartType, ChartOptions, ChartDataSets} from 'chart.js';
import { Label } from 'ng2-charts';

@Component({
  selector: 'new-user-analytics-chart',
  templateUrl: './new-user-analytics-chart.component.html',
  styleUrls: ['./new-user-analytics-chart.component.scss']
})
export class NewUserAnalyticsChartComponent implements OnInit {

  barChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false
    }
  };
  barChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    {data: [45, 37, 60, 70, 46, 33, 45, 37, 60, 70, 46, 33]}
  ];

  constructor() {
  }

  ngOnInit() {
  }

}
