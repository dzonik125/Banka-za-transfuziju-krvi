import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExistingAppointment } from '../model/existingAppointment';

@Injectable({
  providedIn: 'root'
})
export class ExistingAppointmentService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAppointments(): Observable<ExistingAppointment[]>{
    return this.http.get<ExistingAppointment[]>(this.apiHost + 'appSlots/findAllAppointments', {headers: this.headers});
  }

}
