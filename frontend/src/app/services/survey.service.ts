import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Survey } from '../model/survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createSurvey(survey: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'survey', survey);
  }

  getSurveys(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'survey/findAll', {headers: this.headers});
  }

  getSurveyByDonor(id: any) : Observable<Survey> {
    return this.http.get<Survey>(this.apiHost + 'survey/getByDonorId/' + id, {headers: this.headers});
  }

  canDonorDonate(donorId: any): Observable<boolean>{
    let params = new HttpParams();
    params = params.append("id", donorId);
    return this.http.get<boolean>(this.apiHost + 'survey/canDonorDonate', {headers: this.headers, params: params});
  }
}
