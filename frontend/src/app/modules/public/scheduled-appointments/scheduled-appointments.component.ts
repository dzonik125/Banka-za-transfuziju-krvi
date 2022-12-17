import { NotificationService } from './../../../services/notification.service';

import { ExistingAppointment } from './../../../model/existingAppointment';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ExistingAppointmentService } from 'src/app/services/existing-appointment.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { now } from 'moment';
import { DatePipe, formatDate } from '@angular/common';

@Component({
  selector: 'app-scheduled-appointments',
  templateUrl: './scheduled-appointments.component.html',
  styleUrls: ['./scheduled-appointments.component.css']
})
export class ScheduledAppointmentsComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'bloodBank', 'startDate', 'startTime', 'cancel'];
  dataSource: any;
  date!: Date;
  pipe = new DatePipe('en-US'); // Use your own locale
  constructor(private existingAppointmentService: ExistingAppointmentService,
              private jwtHelper: JwtHelperService,
              private notifyService: NotificationService) { }



  ngAfterViewInit(): void {

    this.existingAppointmentService.getAppointments().subscribe(res => {
      const result = res.filter((r: any) => {
        return r.status === 'APPROVED';
      })
      this.dataSource = result;
      //this.dataSource.sort = this.matSort;
    })
  }

  cancelAppointment(event: Event, id: any){
    this.date = new Date();
    this.pipe.transform(this.date.setDate( this.date.getDate() + 1), 'short');
     var request = null;

    request = this.dataSource.filter((res: any) => {
         return res.id === id;
    })

    request[0].donor = this.jwtHelper.decodeToken().id;


    if(this.date > request[0].startTime){
        this.notifyService.showWarning("Ne mozes otkazati termin 1 dan pre pocetka !", "Warning");
    } else{
      this.existingAppointmentService.cancelAppointment(request[0]).subscribe(res=> {
      this.ngAfterViewInit();
      },
      (err) => {
        if(err.status == 409){
          this.notifyService.showWarning("You cannot cancel an appointment 24 hours before the start!", "Warning");
        } else this.notifyService.showWarning("Unexpected error occured", err.status);
      });
    }
  }
}
