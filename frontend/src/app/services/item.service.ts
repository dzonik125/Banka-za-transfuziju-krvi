import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  apiHost: string = 'http://localhost:8081/equipment';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getByBloodBankId(bloodBankId : any) : Observable<any> {
    let params = new HttpParams();
    params = params.append("id", bloodBankId);
    return this.http.get<any>(this.apiHost + '/findAllByBloodBankId', {params: params});
  }
}