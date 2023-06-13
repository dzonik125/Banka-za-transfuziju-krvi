import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { HttpParams } from '@angular/common/http';
import { VisitHistory } from '../model/visitHistory';


@Injectable({
  providedIn: 'root'
})
export class DonorService {

  currentUser!:any;

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  fetchUser(id: any): Observable<User> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<User>(this.apiHost + 'user', {params: params});
  }

  penalizeUser(id : any) : Observable<any> {
    return this.http.put<any>(this.apiHost + 'donor/' + id, {headers: this.headers});
  }

  getVisits(id: any) : Observable<VisitHistory[]> {
    return this.http.get<VisitHistory[]>(this.apiHost + 'donor/findDonorsByBankId/' + id, {headers: this.headers});
  }

}

