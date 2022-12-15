import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { BloodType } from 'src/app/model/bloodType';
import { MonthlySubscription } from 'src/app/model/monthlySubscription';
import { SubscriptionStatus } from 'src/app/model/subcriptionStatus';
import { MonthlySubscriptionsService } from 'src/app/services/monthlySubscriptionService';

@Component({
  selector: 'app-monthly-subscriptions',
  templateUrl: './monthly-subscriptions.component.html',
  styleUrls: ['./monthly-subscriptions.component.css']
})
export class MonthlySubscriptionsComponent implements OnInit {

  subscriptions: any[] = [];
  trigger: number = 0;
  displayedColumns: string[] = [ 'deliveryDate','bloodUnits', 'status', 'approve', 'reject'];
  doctors: any[] = [];

  dataSource = new MatTableDataSource(this.subscriptions);
  constructor(private service:MonthlySubscriptionsService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll()  {
    this.service.getMonthlySubscriptions().subscribe(res=> {
      this.subscriptions = res;
      this.dataSource.data = res;
    });
  }

  approve(subscription: MonthlySubscription) {
    subscription.status = SubscriptionStatus.ACCEPTED
    this.service.update(subscription).subscribe(res=> {
      this.getAll();
    });
    
  }

  reject(subscription: MonthlySubscription) {
    subscription.status = SubscriptionStatus.REJECTED
    this.service.update(subscription).subscribe(res=> {
      this.getAll();
    });
  }

  convertMap(bloodUnits: Map<BloodType, number>) {
    return JSON.stringify(bloodUnits);
  }

  waiting(status: SubscriptionStatus) {
    return status == SubscriptionStatus.WAITING;
  }

}
