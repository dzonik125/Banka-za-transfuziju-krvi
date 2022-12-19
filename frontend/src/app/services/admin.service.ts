import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  
  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAdmins(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'admin', {headers: this.headers});
  }

  createAdministrator(administrator: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'admin/createAdmin', administrator);
  }
}
