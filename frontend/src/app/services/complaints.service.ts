import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintsService {
  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getComplaints(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'complaints/findAllComplaints', {headers: this.headers});
  }

  findAllUnanswered(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'complaints/findAllUnanswered', {headers: this.headers});
  }

  editComplaint(complaint: any, answer: any) : Observable<any> {
    complaint.answer = answer;
    return this.http.put<any>(this.apiHost + 'complaints/answerComplaint', complaint);
  }

}
