import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-reports',
  templateUrl: './view-reports.component.html',
  styleUrls: ['./view-reports.component.css']
})
export class ViewReportsComponent implements OnInit {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  reports: any = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get(this.apiHost + "reports/getReports", {headers: this.headers}).subscribe(res => {
      this.reports = res;
    });
  }

}
