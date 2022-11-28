import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { HttpParams } from '@angular/common/http';
import { ConfigService } from './login-services/config.service';
import { ApiService } from './login-services/api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser!:any;

  apiHost: string = 'http://localhost:8081/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient,
              private config: ConfigService,
              private apiService: ApiService,) { }

  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'auth/signup', user);
  }

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

  getMyInfo() {
    return this.apiService.get(this.config.whoami_url)
      .pipe(map((user: any) => {
        this.currentUser = user;
        return user;
      }));
  }

  getAll() {
    return this.apiService.get(this.config.users_url);
  }
}
