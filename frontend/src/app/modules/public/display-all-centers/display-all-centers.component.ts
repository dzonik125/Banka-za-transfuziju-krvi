import { BloodBankServiceService } from '../../../services/blood-bank-service.service';
import { BloodBank } from 'src/app/model/bloodBank';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {MatSort, Sort} from '@angular/material/sort';
import { MySort } from '../../util/sort';

@Component({
  selector: 'app-display-all-centers',
  templateUrl: './display-all-centers.component.html',
  styleUrls: ['./display-all-centers.component.css']
})
export class DisplayAllCentersComponent implements AfterViewInit {

  public dataSource = new MatTableDataSource<BloodBank>();
  public displayedColumns = ['name', 'address.city', 'avgGrade'];
  //public dataSource2: DataTableDataSource;
  public bloodBanks: BloodBank[] = [];

  gridColumns = 3;
  public sorter: MySort = new MySort();
  public anchor: any;


  constructor(private bloodBankService: BloodBankServiceService, private router: Router, private _liveAnnouncer: LiveAnnouncer) { }



  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodBanks = res;
      this.dataSource.data = this.bloodBanks;
    })
    this.dataSource.sort = this.sort;
  }

/*  sortColumn($event: Sort): void {
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
        case 'address.city': {
          return item.address.city;
        }
        default: {
          return (item as any)[property]; }
      }
    };
}*/


  sortName(property: any){
    this.sorter.sortName(this.bloodBanks,property);
  }

  sortData(){
    this.sorter.sortData(this.bloodBanks);
  }

}
