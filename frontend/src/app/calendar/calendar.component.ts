import { OnInit,  Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import dayGridPlugin from '@fullcalendar/daygrid'; 
import interactionPlugin from '@fullcalendar/interaction'; 
import listPlugin from '@fullcalendar/list';
import { AppointmentService } from '../services/appointment.service';
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

    this.appointmentService.getAppointmentsByBloodBankID(this.id).subscribe(res => {
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
