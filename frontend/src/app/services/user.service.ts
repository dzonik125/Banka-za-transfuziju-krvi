import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser!:any;

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }



  getDonors(): Observable<any[]>{
    return this.http.get<any[]>(this.apiHost + 'donor', {headers: this.headers});
  }

  fetchUser(id: any): Observable<User> {
    let params = new HttpParams();
    params = params.append("id", id);
    return this.http.get<User>(this.apiHost + 'user', {params: params});
  }

  editUser(id:any, user: any): any {
    return this.http.put(this.apiHost + 'user/' + encodeURIComponent(id), user);
  }

}
