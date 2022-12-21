import { BloodBankServiceService } from '../../../services/blood-bank-service.service';
import { BloodBank } from 'src/app/model/bloodBank';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import { MySort } from '../../util/sort';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CreateSurveyComponent } from '../create-survey/create-survey.component';


@Component({
  selector: 'app-display-all-centers',
  templateUrl: './display-all-centers.component.html',
  styleUrls: ['./display-all-centers.component.css'],
  entryComponents: [CreateSurveyComponent]
})
export class DisplayAllCentersComponent implements AfterViewInit {

  public bloodBanks: BloodBank[] = [];
  public term: string = '';
  public sorter: MySort = new MySort();
  public filteredbloodBanks: BloodBank[] = [];
  _minTerm: string = '';
  _maxTerm: string = '';

  title='pagination';
  POSTS: any;
  page: number = 1;
  count: number = 0;

  constructor(private bloodBankService: BloodBankServiceService,
    private router: Router,
    private dialogRef: MatDialog,
    private _liveAnnouncer: LiveAnnouncer
    ) { }

    ngAfterViewInit() {
      this.bloodBankService.getBloodBanks().subscribe(res => {
        this.bloodBanks = res;
        this.filteredbloodBanks = this.bloodBanks;
      })
    }

  public get minTerm(): string {
    return this._minTerm;
  }

  public set minTerm(value: string) {
    this._minTerm = value;
    if(value === '' && this._maxTerm !== ''){
      this.filteredbloodBanks = this.filterBanksByMaxGrade(this._maxTerm);
      return;
    }
    if(this._maxTerm !== '') {
      this.filteredbloodBanks = this.filterBanksByMinMaxGrade(this._maxTerm, value);
    } else {
      this.filteredbloodBanks = this.filterBanksByMinGrade(value);
    }
  }

  public get maxTerm(): string {
    return this._maxTerm;
  }

  public set maxTerm(value: string) {
    this._maxTerm = value;
    if(value === '' && this._minTerm !== ''){
      this.filteredbloodBanks = this.filterBanksByMinGrade(this._minTerm);
      return;
    }
    if (this._minTerm !== '') {
      this.filteredbloodBanks = this.filterBanksByMinMaxGrade(value, this._minTerm);
    } else {
      this.filteredbloodBanks = this.filterBanksByMaxGrade(value);
    }
  }



  sortName(property: any){
    this.sorter.sortName(this.filteredbloodBanks, property);
  }

  sortData(){
    this.sorter.sortData(this.filteredbloodBanks);
  }

  filterBanksByMinGrade(filterTerm: string){
    if(this.bloodBanks.length === 0 || filterTerm === '') {
      return this.bloodBanks;
    } else {
      return this.bloodBanks.filter((bank) => {
        return bank.avgGrade! >= parseFloat(filterTerm)
      })
    }
  }

  filterBanksByMaxGrade(filterTerm: string){
    if(this.bloodBanks.length === 0 || filterTerm === '') {
      return this.bloodBanks;
    } else {
      return this.bloodBanks.filter((bank) => {
        return bank.avgGrade! <= parseFloat(filterTerm)
      })
    }
  }

  filterBanksByMinMaxGrade(max: string, min: string){
    if(this.bloodBanks.length === 0 || (max === '' && min === '')) {
      return this.bloodBanks;
    } else {
      return this.bloodBanks.filter((bank) => {
        return parseFloat(min) <= bank.avgGrade! && bank.avgGrade! <= parseFloat(max);
      })
    }
  }

  showBank(id: any) {
    this.router.navigate(['bloodBank/' + id]);
  }
  
  showCalendar(id: any) {
    this.router.navigate(['adminDashboard/calendar/'+id]);
  }


  onTableDataChange(event: any){
    this.page = event;
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodBanks = res;
      this.filteredbloodBanks = this.bloodBanks;
    })
  }
}
