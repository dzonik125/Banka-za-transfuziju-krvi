import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicalWorkerService {

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createMedicalWorker(medicalWorker: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'medicalWorker', medicalWorker);
  }

  getMedicalWorkers(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'medicalWorker', {headers: this.headers});
  }

  
  getFreeMedicalWorkers(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'medicalWorker/freeMedicalWorkers', {headers: this.headers});
  }

  getAllByBloodBank(bloodBankId: any): Observable<any[]> {
    let params = new HttpParams();
    params = params.append("bloodBankId", bloodBankId);
    return this.http.get<any[]>(this.apiHost + 'medicalWorker/getAllByBloodBank', {params: params});

  }

  updateMedicalWorker(medicalWorker: any) {
    return this.http.put<any>(this.apiHost + 'medicalWorker', medicalWorker);
  }

}
