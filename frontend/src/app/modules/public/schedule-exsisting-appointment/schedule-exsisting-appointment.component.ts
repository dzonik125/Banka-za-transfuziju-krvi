import { ExistingAppointmentService } from './../../../services/existing-appointment.service';
import { ExistingAppointment } from './../../../model/existingAppointment';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-schedule-exsisting-appointment',
  templateUrl: './schedule-exsisting-appointment.component.html',
  styleUrls: ['./schedule-exsisting-appointment.component.css']
})
export class ScheduleExsistingAppointmentComponent implements OnInit {

  public appointments: ExistingAppointment[] = [];
  constructor(private existingAppointmentService: ExistingAppointmentService) { }

  ngOnInit() {
    this.existingAppointmentService.getAppointments().subscribe(res => {
      this.appointments = res;
    })
  }



}
