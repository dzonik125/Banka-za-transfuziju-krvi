import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  getAppointmentById(id: any) : Observable<ExistingAppointment> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<ExistingAppointment>(this.apiHost + 'appSlots/getById', {params: params});
  }


  scheduleAppointment(request: any){
  return this.http.put<any>(this.apiHost + 'appSlots/updateAppointment', request, {headers: this.headers})
  }

cancelAppointment(request: any){
  return this.http.put<any>(this.apiHost + 'appSlots/cancelAppointment', request, {headers: this.headers})
  }

}
