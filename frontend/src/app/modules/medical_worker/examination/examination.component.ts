import { Component, Inject, AfterViewInit, ViewEncapsulation, OnInit } from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { JwtHelperService } from '@auth0/angular-jwt';
import { toJSDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-calendar';
import { AppointmentStatus } from 'src/app/model/AppointmentStatus';
import { Appointment } from 'src/app/model/appointment';
import { AppointmentHistory } from 'src/app/model/appointmentHistory';
import { Item } from 'src/app/model/item';
import { Survey } from 'src/app/model/survey';
import { AppointmentHistoryService } from 'src/app/services/appointement-history.service';
import { AppointmentService } from 'src/app/services/appointment.service';
import { DonorService } from 'src/app/services/donor.service';
import { ItemService } from 'src/app/services/item.service';
import { SurveyService } from 'src/app/services/survey.service';
import { Gender } from '../../util/enum/gender';
@Component({
  selector: 'app-examination',
  templateUrl: './examination.component.html',
  styleUrls: ['./examination.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ExaminationComponent implements OnInit {

  public surveys: any;
  public donorSurvey: Survey = new Survey();
  public medicalWorkerId: any;
  public items!: Item[];
  public dataSource = new MatTableDataSource<Item>();
  public dataSourceUsed = new MatTableDataSource<Item>();
  public displayedColumns = ['name', 'quantity'];
  public selectedItem: Item = new Item();
  public itemsUsed!: Item[];
  public quantity: number =1;
  public selectedType: string = "";
  public appHistory: AppointmentHistory = new AppointmentHistory();
  public canExamine: boolean = true;
  public female!: boolean;
  constructor(@Inject(MAT_DIALOG_DATA) public data : any,
              private surveyService : SurveyService,
              private jwtHelper : JwtHelperService,
              private itemService : ItemService,
              private appHistoryService : AppointmentHistoryService,
              private appService : AppointmentService,
              private donorService: DonorService,
              private DialogRef: MatDialogRef<ExaminationComponent>,
              ) { }
  

  ngOnInit(): void {
    this.surveyService.getSurveyByDonor(this.data.donorId).subscribe(res =>{
      this.donorSurvey = res;
      console.log(this.donorSurvey);
    
    })
    this.donorService.fetchUser(this.data.donorId).subscribe(res=>{
      console.log(res.gender)
      this.female = res.gender == "FEMALE";
    })
    this.surveyService.canDonorDonate(this.data.donorId).subscribe(res => {
      this.canExamine = res;
      if(!this.canExamine) {
        window.alert("Based on survey, patient is not fit to donate blood");
      }
    })
    this.medicalWorkerId = this.jwtHelper.decodeToken().id;
  
    this.itemService.getByBloodBankId(this.data.bloodBankId).subscribe(
      res=> {
        this.items = res;
        this.dataSource.data = this.items
      }
    )
    this.itemsUsed = [];
    
  }

  select(item: Item) {
    this.selectedItem = (JSON.parse(JSON.stringify(item))) as Item;
  }

  addUsed() {

    const index = this.itemsUsed.findIndex(obj => obj.id === this.selectedItem.id);
    if (index !== -1) {
      if(this.selectedItem.quantity + this.quantity < 0) {
        window.alert("Quantity can't be less than zero")
        return;
      }
      this.selectedItem.quantity += this.quantity;
      this.itemsUsed[index] = this.selectedItem;
    }else{
      if(this.quantity < 0) {
        window.alert("Quantity can't be less than zero")
        return;
      }
      this.selectedItem.quantity = this.quantity;
      this.itemsUsed.push(this.selectedItem);
    }
      this.dataSourceUsed.data = this.itemsUsed;
    }

  finishExamination() {
    if(this.itemsUsed.length === 0) {
      window.alert("You must select equipment");
      return;
    }
    const index = this.itemsUsed.findIndex(obj => obj.quantity === 0);
    if(index !== -1) {
      window.alert("You must select equipment");
      return;
    }
    if(this.selectedType === "" ) {
      window.alert("You must select blood type");
      return;
    }
    if(confirm("Are you sure?")) {
      this.appHistory.amount = 0.4;
      this.appHistory.bloodBankId = this.data.bloodBankId;
      this.appHistory.donorId = this.data.donorId;
      this.appHistory.bloodType = this.selectedType;
      this.appHistory.medicalWorkerId = this.medicalWorkerId;
      this.appHistory.item = this.itemsUsed;
      this.appHistory.status = AppointmentStatus.APPROVED;
      this.appHistory.appointmentId = this.data.id;
      this.appHistoryService.createAppointmentHistory(this.appHistory).subscribe();
      this.DialogRef.close()
    }
  }

  reject() {
    if(confirm("Are you sure?")) {
      console.log(this.data.id);
      this.appService.cancelAppointment(this.data.id).subscribe();
      this.DialogRef.close()
    }
  }

  penalizeUser() {
    if(confirm("Are you sure?")) {
      this.appService.cancelAppointment(this.data.id).subscribe(res => {
        this.donorService.penalizeUser(this.data.donorId).subscribe();
        this.DialogRef.close()
      });
    }
  }

  examine() {
    if(!this.canExamine) {
      window.alert("Based on survey, patient is not fit to donate blood");
    }
  }
}
