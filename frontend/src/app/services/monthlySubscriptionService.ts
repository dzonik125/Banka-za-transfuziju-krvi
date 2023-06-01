import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { MonthlySubscription } from "../model/monthlySubscription";

@Injectable({
    providedIn: 'root'
  })
  export class MonthlySubscriptionsService {
  
  
    apiHost: string = 'http://localhost:8081/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }
  
    getMonthlySubscriptions(): Observable<MonthlySubscription[]>{
      return this.http.get<MonthlySubscription[]>(this.apiHost + 'monthlySubscriptions', {headers: this.headers});
    }

    update(subscription : MonthlySubscription) {
        return this.http.put<MonthlySubscription>(this.apiHost + 'monthlySubscriptions', subscription, {headers: this.headers});
    }
  
  }
  