import { Component, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import dayGridPlugin from '@fullcalendar/daygrid'; 
import interactionPlugin from '@fullcalendar/interaction'; 
import listPlugin from '@fullcalendar/list';
import { ExistingAppointment } from '../model/existingAppointment';



@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})


export class CalendarComponent implements OnInit {

  Events: any[] = [
    { title: 'Marko markovic', date: '2023-01-01', start: '2023-01-01T10:30:00',  },
  ];

  
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin, listPlugin],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth, dayGridWeek, dayGridDay, listWeek'
    },
    dayMaxEvents: true, // allow "more" link when too many events
    events: [{ title: 'Marko markovic', date: '2023-01-01', start: '2023-01-01T10:30:00',  end:'2023-01-01T11:30:00' }]

  };


  constructor() { }

  ngOnInit(): void {
  }


  calendarPlugins = [dayGridPlugin]; 
}
