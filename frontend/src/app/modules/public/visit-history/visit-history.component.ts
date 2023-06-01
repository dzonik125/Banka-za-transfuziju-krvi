import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ExistingAppointment } from 'src/app/model/existingAppointment';
import { ExistingAppointmentService } from 'src/app/services/existing-appointment.service';

@Component({
  selector: 'app-visit-history',
  templateUrl: './visit-history.component.html',
  styleUrls: ['./visit-history.component.css']
})
export class VisitHistoryComponent implements AfterViewInit {

  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['id', 'bloodBank', 'startDate', 'startTime'];
  dataSource = new MatTableDataSource<ExistingAppointment>();


  constructor(private existingAppointmentService: ExistingAppointmentService,
              private jwtHelper: JwtHelperService,
              private _liveAnnouncer: LiveAnnouncer) { }


  ngAfterViewInit(): void {
    this.existingAppointmentService.getAppointments().subscribe(res => {
      const result = res.filter((r: any) => {
        return r.status === 'APPROVED' && new Date(r.startTime) < new Date() && r.donor.id == this.jwtHelper.decodeToken().id;
      })
      this.dataSource = new MatTableDataSource<ExistingAppointment>(result);
      this.dataSource.sort = this.sort;
    })
  }

  announceSortChange(sortState: Sort) {
    this.dataSource.sort = this.sort;
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

}
