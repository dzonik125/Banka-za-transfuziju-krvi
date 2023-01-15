import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicalExaminationService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  pic: any;

  constructor(private http: HttpClient) { }

  getQR(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'newController', {headers: this.headers});
  }

}
