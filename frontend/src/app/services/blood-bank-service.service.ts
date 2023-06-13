import { BloodBank } from 'src/app/model/bloodBank';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankBlood } from '../model/bloodBankBlood';

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
    return this.http.get<BloodBank[]>(this.apiHost + 'bloodBanks/findAllBloodBanks', {headers: this.headers});
  }

  getBloodBanksByName(currentPage: any): Observable<BloodBank[]>{
    let params = new HttpParams();
    params = currentPage;
    return this.http.get<BloodBank[]>(this.apiHost + 'bloodBanks/findAll', {headers: this.headers, params: params});
  }

  getBloodBankById(id: any) : Observable<BloodBank> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<BloodBank>(this.apiHost + 'bloodBanks/getById', {params: params});
  }


  editBloodBank(BloodBank: any) : Observable<BloodBank> {
    return this.http.put<any>(this.apiHost + 'bloodBanks/updateBloodBank', BloodBank);
  }

  getApiKey(id: any) : Observable<any> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get(this.apiHost + 'bloodBanks/getApiKeyById', {params: params, responseType: 'text'});
  }

  getBlood(id: any) : Observable<BloodBankBlood>{
    return this.http.get<BloodBankBlood>(this.apiHost + 'bloodBanks/getBloodBankBlood/' + id, {headers: this.headers});
  }

}
