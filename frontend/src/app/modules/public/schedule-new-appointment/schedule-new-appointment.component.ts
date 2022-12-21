import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-schedule-new-appointment',
  templateUrl: './schedule-new-appointment.component.html',
  styleUrls: ['./schedule-new-appointment.component.css']
})
export class ScheduleNewAppointmentComponent implements OnInit {

  //Ng model variables
  date: Date = new Date();
  startTime: string = '';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  ScheduleApp1(): void {
    let splitted = this.startTime.split(":");
    let hour = splitted[0];
    let min = splitted[1];
    this.date = new Date(this.date);
    this.date.setHours(Number(hour));
    this.date.setMinutes(Number(min));
    
    this.http.post("http://localhost:8081/appSlots/getAvailableBanks", this.date).subscribe(res => {
      console.log(res);
    })
  }

}
