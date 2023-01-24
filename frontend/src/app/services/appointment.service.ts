import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAppointments(): Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.apiHost + 'appointments/findAllAppointments', {headers: this.headers});
  }


  getAppointmentsByBloodBankID(id: any): Observable<Appointment[]>{
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<Appointment[]>(this.apiHost + 'appointments/findAllAppointmentsByBloodBankID', { params: params, headers: this.headers});
  }


  getAppointmentById(id: any) : Observable<Appointment> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<Appointment>(this.apiHost + 'appointments/getById', {params: params});
  }


/*
  getBloodBankById(id: any) : Observable<BloodBank> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<BloodBank>(this.apiHost + 'bloodBanks/getById', {params: params});
  } */
}
