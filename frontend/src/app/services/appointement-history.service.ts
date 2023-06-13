import { BloodBank } from 'src/app/model/bloodBank';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentHistoryService {


  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createAppointmentHistory(appointmentHistory: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'appointmentHistories', appointmentHistory);
  }

}
