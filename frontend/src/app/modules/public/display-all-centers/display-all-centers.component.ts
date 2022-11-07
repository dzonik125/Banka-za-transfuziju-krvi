import { BloodBankServiceService } from '../../services/blood-bank-service.service';
import { BloodBank } from 'src/app/model/bloodBank';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-display-all-centers',
  templateUrl: './display-all-centers.component.html',
  styleUrls: ['./display-all-centers.component.css']
})
export class DisplayAllCentersComponent implements OnInit {

  public dataSource = new MatTableDataSource<BloodBank>();
  public displayedColumns = ['name', 'city', 'avg grade'];
  public bloodBanks: BloodBank[] = [];
  constructor(private bloodBankService: BloodBankServiceService, private router: Router) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodBanks = res;
      this.dataSource.data = this.bloodBanks;
    })
  }

}
