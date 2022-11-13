import { BloodBank } from 'src/app/model/bloodBank';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BloodBankServiceService {


  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createBloodBank(bloodBank: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'bloodBanks', bloodBank);
  }

  getBloodBanks(): Observable<BloodBank[]>{
    return this.http.get<BloodBank[]>(this.apiHost + 'bloodBanks', {headers: this.headers});
  }

  getBloodBankById(id: any) : Observable<BloodBank> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<BloodBank>(this.apiHost + 'bloodBanks/getById', {params: params});
  }
}
