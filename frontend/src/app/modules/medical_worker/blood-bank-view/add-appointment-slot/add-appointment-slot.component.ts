import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppointmentSlot } from 'src/app/model/appointmentSlot';
import { AppointmentStatus } from 'src/app/model/AppointmentStatus';
import { BloodBank } from 'src/app/model/bloodBank';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-appointment-slot',
  templateUrl: './add-appointment-slot.component.html',
  styleUrls: ['./add-appointment-slot.component.css']
})
export class AddAppointmentSlotComponent implements OnInit {

  //Ng model variables
  date: Date = new Date();
  startTime: string = '';
  duration: number = 0;
  medWorkerId: string = '';
  startWorkTime: string = '';
  endWorkTime: string = '';
  resData: any;
  bank: BloodBank | null = null;

  constructor(private http: HttpClient, private notifyService : NotificationService, private userService: UserService, private jwtHelper: JwtHelperService) { }

  ngOnInit(): void {
    this.medWorkerId = this.jwtHelper.decodeToken().id as string;
    this.getBloodBankWorkingHours();
  }

  getBloodBankWorkingHours(): any{
    this.http.get('http://localhost:8081/medicalWorker/getBloodBankWorkingHours', { params: { id: this.medWorkerId }}).subscribe(res => {
      this.resData = res;
      this.startWorkTime = this.resData.startTime;
      this.endWorkTime = this.resData.endTime;
      this.bank = this.resData.bloodBank;
    })
  }

  createAppointmentSlot(){
    if(new Date(this.date) < new Date){
      this.notifyService.showWarning("You can't pick a date less than today!", "Warning");
      return;
    }

    if(this.startTime === '' || this.duration === 0){
      this.notifyService.showWarning("All fields mustn't be empty!", "Warning");
      return;
    }
    
    let splitted = this.startTime.split(":");
    let hour = splitted[0];
    let min = splitted[1];
    let endDate = new Date(this.date);
    this.date = new Date(this.date);
    this.date.setHours(Number(hour));
    this.date.setMinutes(Number(min));
    endDate.setHours(this.date.getHours());
    endDate.setMinutes(this.date.getMinutes() + this.duration);
    splitted = this.startWorkTime.split(":");
    hour = splitted[0];
    min = splitted[1];
    let todayStartWorkTime = new Date(this.date);
    todayStartWorkTime.setHours(Number(hour));
    todayStartWorkTime.setMinutes(Number(min));
    let todayEndWorkTime = new Date(this.date);
    splitted = this.endWorkTime.split(":");
    hour = splitted[0];
    min = splitted[1];
    todayEndWorkTime.setHours(Number(hour));
    todayEndWorkTime.setMinutes(Number(min));
    if(!(todayStartWorkTime.valueOf() <= this.date.valueOf()) || !(todayEndWorkTime.valueOf() >= this.date.valueOf()) || !(todayStartWorkTime.valueOf() <= endDate.valueOf()) || !(todayEndWorkTime.valueOf() >= endDate.valueOf())){
      this.notifyService.showWarning("You are out of working hours! Working hours: " + this.startWorkTime + " - " + this.endWorkTime, "Warning");
    } else {
      if(this.bank){
        let slot = new AppointmentSlot(this.bank, this.date, endDate, AppointmentStatus.WAITING);
        this.http.post("http://localhost:8081/appSlots", slot).subscribe(res => {this.notifyService.showSuccess("Successfully added!", "Success")}, err => {
          this.notifyService.showError("Appointment in that time range already exists!", "Error");
        });
      }
    }
  }

}
