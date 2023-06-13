import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { JwtHelperService } from '@auth0/angular-jwt';
import { VisitHistory } from 'src/app/model/visitHistory';
import { DonorService } from 'src/app/services/donor.service';
import { MedicalWorkerService } from 'src/app/services/medical-worker.service';

@Component({
  selector: 'app-donor-visited',
  templateUrl: './donor-visited.component.html',
  styleUrls: ['./donor-visited.component.css']
})
export class DonorVisitedComponent implements OnInit {
  @ViewChild(MatSort) sort!: MatSort;
  displayedColumns: string[] = ['name', 'surname', 'type', 'startTime'];
  dataSource = new MatTableDataSource<any>();
  constructor(private jwtHelper: JwtHelperService,
              private donorService: DonorService,
              private medicalWorkerService: MedicalWorkerService,
              private _liveAnnouncer: LiveAnnouncer) { }

  ngOnInit(): void {
    this.medicalWorkerService.getBloodBankId(this.jwtHelper.decodeToken().id).subscribe(res=> {
      this.donorService.getVisits(res).subscribe(result=> {
        this.dataSource = new MatTableDataSource<any>(result);
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'startTime': {
              return new Date(item.donationTime);
            }
            default: {
              return item[property];
            }
          };
        }
        this.dataSource.sort = this.sort;
      })
    })
  }


}
