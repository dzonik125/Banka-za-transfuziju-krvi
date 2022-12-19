import { OnInit,  Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import dayGridPlugin from '@fullcalendar/daygrid'; 
import interactionPlugin from '@fullcalendar/interaction'; 
import listPlugin from '@fullcalendar/list';
import { toJSDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-calendar';
import { Appointment } from '../model/appointment';
import { ExistingAppointment } from '../model/existingAppointment';
import { AppointmentService } from '../services/appointment.service';
import { setTimeout } from "timers/promises";
import { concat } from 'rxjs';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})


export class CalendarComponent implements OnInit {

  public appointmets: any[] = [
  ];
  
  public id: any;

  constructor(private appointmentService: AppointmentService, private route: ActivatedRoute) { }




  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    window.alert(this.id)

    this.appointmentService.getAppointmentsByBloodBankID(this.id).subscribe(res => {
      // window.alert(JSON.stringify(res[0]))
      // this.appointmets.push(res[0])
      // this.appointmets.push(res[1])
     res.forEach(element => this.appointmets.push(element));

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
    events: this.appointmets,

  };

}