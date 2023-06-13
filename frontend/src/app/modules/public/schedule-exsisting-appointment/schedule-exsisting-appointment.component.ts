import { SurveyService } from 'src/app/services/survey.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { HttpRequest } from '@angular/common/http';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';
import { AppComponent } from './../../../app.component';
import { ExistingAppointmentService } from './../../../services/existing-appointment.service';
import { ExistingAppointment } from './../../../model/existingAppointment';
import { AfterViewInit, Component, OnInit, ViewChild, enableProdMode, PipeTransform } from '@angular/core';
import { DatePipe, formatDate } from '@angular/common';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Donor } from 'src/app/model/donor';
import * as moment from 'moment';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { NotificationService } from 'src/app/services/notification.service';
import { ExistingAppointmentDTO } from 'src/app/model/dto/existingAppointmentDTO';

@Component({
  selector: 'app-schedule-exsisting-appointment',
  templateUrl: './schedule-exsisting-appointment.component.html',
  styleUrls: ['./schedule-exsisting-appointment.component.css']
})


export class ScheduleExsistingAppointmentComponent implements AfterViewInit {

  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['id', 'bloodBank', 'startDate', 'startTime', 'schedule'];
  dataSource = new MatTableDataSource<ExistingAppointment>();
  userClaims: any;
  user!: any;
  retApp: ExistingAppointmentDTO[] = [];
  surveys: any;
  //currentDate: Date = new Date();

  constructor(private existingAppointmentService: ExistingAppointmentService,
              private userService: UserService,
              private jwtHelper: JwtHelperService,
              private _liveAnnouncer: LiveAnnouncer,
              private notifyService : NotificationService,
              private surveyService: SurveyService,
              ) {

              //let currentDateTime =this.datepipe.((new Date), 'MM/dd/yyyy h:mm:ss');
               }

  ngAfterViewInit() {

    this.surveyService.getSurveys().subscribe(res =>{
      this.surveys = res;
    })

    this.userService.fetchUser(this.jwtHelper.decodeToken().id).subscribe(res => {
        this.user = res;
        if(this.user.hasSurvey == false){
          this.notifyService.showInfo("You must fill out the survey to schedule an appointment!", "Info");
        }
    })




    this.existingAppointmentService.getAppointments().subscribe(res => {
      const result = res.filter((r: any) => {

        return r.status === 'WAITING' && new Date(r.startTime) > new Date();
      })
      this.dataSource = new MatTableDataSource<ExistingAppointment>(result);
      this.dataSource.sort = this.sort;
      })

  }



  async scheduleAppointment(event: Event, id: any){
    var request = null;
    const element = event.target as HTMLElement;
    const s = this.surveys.filter((res: any) => {
        return res.donor.id === this.jwtHelper.decodeToken().id
    })

    request = this.dataSource.data.filter((res: any) => {
         return res.id === id;
    })

    request[0].donor = this.jwtHelper.decodeToken().id;
    request[0].status = "APPROVED";
    if(s[0].answer6 === 'yes'){
      this.notifyService.showWarning("You cannot schedule an appointment because you have donated blood in the previous 6 months !", "Warning");
    } else{
    this.existingAppointmentService.scheduleAppointment(request[0]).subscribe(res=> {
      this.ngAfterViewInit();
    },
      (err) => {
        //if(err.status != 409){
        this.notifyService.showWarning(err.error.message, "Warning");
        //} else this.notifyService.showWarning(err.error.message, "Warrning");
      });
    }
    this.ngAfterViewInit();
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
}
