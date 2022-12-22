import { LiveAnnouncer } from '@angular/cdk/a11y';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { firstValueFrom } from 'rxjs';
import { AppointmentDTO } from 'src/app/model/appntmnt';
import { BloodBank } from 'src/app/model/bloodBank';
import { MedicalWorker } from 'src/app/model/medicalWorker';
import { ExistingAppointmentService } from 'src/app/services/existing-appointment.service';
import { NotificationService } from 'src/app/services/notification.service';
import { SurveyService } from 'src/app/services/survey.service';
import { UserService } from 'src/app/services/user.service';
import { MySort } from '../../util/sort';

@Component({
  selector: 'app-schedule-new-appointment',
  templateUrl: './schedule-new-appointment.component.html',
  styleUrls: ['./schedule-new-appointment.component.css']
})
export class ScheduleNewAppointmentComponent implements OnInit {

  //Ng model variables
  date: Date = new Date();
  startTime: string = '';
  bloodBanks: any[] = [];
  fetching = false;
  user!: any;
  surveys: any;
  public sorter: MySort = new MySort();
  schedButton = true;

  constructor(private http: HttpClient, private existingAppointmentService: ExistingAppointmentService,
    private userService: UserService,
    private jwtHelper: JwtHelperService,
    private _liveAnnouncer: LiveAnnouncer,
    private notifyService : NotificationService,
    private surveyService: SurveyService) { }

  ngOnInit(): void {
    this.userService.fetchUser(this.jwtHelper.decodeToken().id).subscribe(res => {
      this.user = res;
      if(this.user.hasSurvey == false){
        this.notifyService.showInfo("You must fill out the survey to schedule an appointment!", "Info");
        this.schedButton = false;
      }
  })

    this.surveyService.getSurveys().subscribe(res =>{
      this.surveys = res;
    })
  }

  ScheduleApp1(): void {
    this.fetching = true;
    let splitted = this.startTime.split(":");
    let hour = splitted[0];
    let min = splitted[1];
    this.date = new Date(this.date);
    this.date.setHours(Number(hour));
    this.date.setMinutes(Number(min));
    
    this.http.post<any[]>("http://localhost:8081/appSlots/getAvailableBanks", this.date).subscribe(res => {
      this.fetching = false;
      this.bloodBanks = res;
    })
  }

  SortByGrade(grade: any): any{
    this.sorter.sortName(this.bloodBanks, grade);
  }

  async ScheduleApp(id: any): Promise<void>{
    const s = this.surveys.filter((survey: any) => {
      return survey.donor.id === this.jwtHelper.decodeToken().id
    });

    if(s[0].answer6 === 'yes') {
      this.notifyService.showWarning("You cannot schedule an appointment because you have donated blood in the previous 6 months !", "Warning");
      return;
    }

    const t = await firstValueFrom(this.http.get<MedicalWorker[]>('http://localhost:8081/medicalWorker/getAllByBloodBank', {params: {"bloodBankId": id}}));
    const b = await firstValueFrom(this.http.get<BloodBank>('http://localhost:8081/bloodBanks/getById', {params: {"id": id}}));

    console.log(t);
    var appntmnt = new AppointmentDTO([t[0].id], this.jwtHelper.decodeToken().id, b, this.date, 30);
    console.log(appntmnt);

    this.http.post('http://localhost:8081/appointments/scheduleAppointment', appntmnt).subscribe(response => {
      this.notifyService.showSuccess("You have scheduled your appointment successfully!", 'Success');
      window.location.reload();
    },
    (error) => {
      this.notifyService.showError("There was an error", "Error");
    });

  }

}
