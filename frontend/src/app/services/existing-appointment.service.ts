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

  changeRequestStatus(request: any){
    console.log(request);
    // let time = new Date(request.creationDate).getTime();
    // let day = new Date(request.creationDate).getDate();
    // let month = new Date(request.creationDate).getMonth() + 1;
    // let year = new Date(request.creationDate).getFullYear();
    // let newFormat = `${day}/${month}/${year}`;
    // request.creationDate = newFormat;
    // console.log(request.creationDate);
return this.http.put<any>(this.apiHost + 'appSlots/updateAppointment', request, {headers: this.headers})
}



}
