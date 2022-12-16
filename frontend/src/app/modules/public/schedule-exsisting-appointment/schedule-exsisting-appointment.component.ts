import { SurveyService } from 'src/app/services/survey.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { HttpRequest } from '@angular/common/http';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';
import { AppComponent } from './../../../app.component';
import { ExistingAppointmentService } from './../../../services/existing-appointment.service';
import { ExistingAppointment } from './../../../model/existingAppointment';
import { AfterViewInit, Component, OnInit, ViewChild, enableProdMode } from '@angular/core';
import { DatePipe, formatDate } from '@angular/common';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Donor } from 'src/app/model/donor';
import * as moment from 'moment';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { NotificationService } from 'src/app/services/notification.service';
import { ExistingAppointmentDTO } from 'src/app/model/dto/existingAppointmentDTO';

@Component({
  selector: 'app-schedule-exsisting-appointment',
  templateUrl: './schedule-exsisting-appointment.component.html',
  styleUrls: ['./schedule-exsisting-appointment.component.css']
})
export class ScheduleExsistingAppointmentComponent implements AfterViewInit {

   appointments: ExistingAppointment[] = [];

  displayedColumns: string[] = ['id', 'bloodBank', 'startDate', 'startTime', 'schedule', 'cancel'];
  dataSource: any;
  userClaims: any;
  user!: any;
  retApp: ExistingAppointmentDTO[] = [];

  surveys: any;

  constructor(private existingAppointmentService: ExistingAppointmentService,
              private userService: UserService,
              private jwtHelper: JwtHelperService,
              private _liveAnnouncer: LiveAnnouncer,
              private notifyService : NotificationService,
              private surveyService: SurveyService) { }

  @ViewChild(MatSort) sort!: MatSort;

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
        return r.status === 'WAITING';
      })
      this.dataSource = result;
    })


    this.dataSource.sort = this.sort;

  }


  scheduleAppointment(event: Event, id: any){
    var request = null;
    const element = event.target as HTMLElement;
    const s = this.surveys.filter((res: any) => {
        return res.donor.id === this.jwtHelper.decodeToken().id
    })

    request = this.dataSource.filter((res: any) => {
         return res.id === id;
    })

    request[0].donor = this.jwtHelper.decodeToken().id;
    request[0].status = 1;
    if(s[0].answer6 === 'yes'){
      this.notifyService.showWarning("You cannot schedule an appointment because you have donated blood in the previous 6 months !", "Warrning");
    } else{
    this.existingAppointmentService.scheduleAppointment(request[0]).subscribe(res=> {
      this.ngAfterViewInit();
      });
    }


      // if(element.innerText === 'Approve'){
      //    request = this.requests.filter(req => {
      //     return req.id === id;
      //   })
      //   request[0].status = 1;
      //   this.service.changeRequestStatus(request[0]).subscribe(res=> {
      //     this.ngOnInit();
      //  });
      // }
      // else if(element.innerHTML === 'Reject') {
      //   request = this.requests.filter(req => {
      //     return req.id === id;
      //   })
      //   request[0].status = 0;
      //   this.service.changeRequestStatus(request[0]).subscribe(res=> {
      //     this.ngOnInit();
      //  });

      // }
      // else {
      //   request = this.requests.filter(req => {
      //     return req.id === id;
      //   })

      // }
  }

  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }






}
