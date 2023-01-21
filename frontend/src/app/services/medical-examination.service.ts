import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicalExaminationService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }


  createMedicalExamination(medicalExaminationDTO: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'medicalExamination/createME', medicalExaminationDTO);
  }

}
