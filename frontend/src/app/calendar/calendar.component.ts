import { OnInit,  Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import dayGridPlugin from '@fullcalendar/daygrid'; 
import interactionPlugin from '@fullcalendar/interaction'; 
import listPlugin from '@fullcalendar/list';
import { AppointmentService } from '../services/appointment.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ExaminationComponent } from '../modules/medical_worker/examination/examination.component';
import { Appointment } from '../model/appointment';
import { MedicalWorkerService } from '../services/medical-worker.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})


export class CalendarComponent implements OnInit {

  public appointmets: Appointment[] = [
  ];
  
  public id: any;
  public isAppointmentNow!: boolean;
  constructor(private appointmentService: AppointmentService, 
    private route: ActivatedRoute, private dialog: MatDialog,
    private medicalWorkerService: MedicalWorkerService,
    private jwtHelper: JwtHelperService) { }


  ngOnInit() {
    this.medicalWorkerService.getBloodBankId(this.jwtHelper.decodeToken().id).subscribe(res=> {
      this.id = res;
      this.appointmentService.getAppointmentsByBloodBankID(this.id).subscribe(res => {
        res.forEach(element => {
           if(element.status === "WAITING") {
             this.appointmets.push(this.handle(element))
           }
        });
       })
    })
  }

  handle(element: Appointment) : any {
    return { //only affects sidebar - loses data on drop
                
      title:  element.title,
      start: element.start,
       extendedProps: {
           id: element.id,
           donorId: element.donorId,   
       }	
  }
  }
  handleEventClick(event: any) {
    this.appointmentService.isAppointmentNow(event.event.extendedProps.id).subscribe(res=> {
      this.isAppointmentNow = res;
      if(!this.isAppointmentNow) {
        window.alert("You cannot start examination at this moment");
        return;
      }else{
        this.dialog.open(ExaminationComponent, {
          width: '50%',
          height: '80%',
          data: {
            id : event.event.extendedProps.id,
            donorId : event.event.extendedProps.donorId,
            bloodBankId: this.id
          }
        })
      }      
    })
  }

  
  calendarPlugins = [dayGridPlugin];   
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin, listPlugin],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth, dayGridWeek, dayGridDay, listWeek'
    },
    dayMaxEvents: true, // allow "more" link when too many events
    events: this.appointmets as any,
    eventClick: this.handleEventClick.bind(this)
  };

}

