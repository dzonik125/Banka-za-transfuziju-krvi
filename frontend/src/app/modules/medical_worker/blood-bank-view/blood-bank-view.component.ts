import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BloodBank } from 'src/app/model/bloodBank';
import { BloodBankServiceService } from 'src/app/services/blood-bank-service.service';
import {Input, NgZone} from '@angular/core';
import { Validator } from '../../util/validation';
import { MedicalWorkerService } from 'src/app/services/medical-worker.service';
import { MatTableDataSource } from '@angular/material/table';
import { MedicalWorker } from 'src/app/model/medicalWorker';
import { MatDialog } from '@angular/material/dialog';
import { AddWorkerToBankDialogComponent } from './add-worker-to-bank-dialog/add-worker-to-bank-dialog.component';

@Component({
  selector: 'app-blood-bank-view',
  templateUrl: './blood-bank-view.component.html',
  styleUrls: ['./blood-bank-view.component.css']
})
export class BloodBankViewComponent implements OnInit {

  id: any;
  editName: boolean = false;
  editCountry: boolean = false;
  editCity: boolean = false;
  editStreet: boolean = false;
  editNumber: boolean = false;
  editDescription: boolean = false;
  bloodBank!: BloodBank;
  public validation: Validator = new Validator;

  public dataSource = new MatTableDataSource<MedicalWorker>();
  public displayedColumns = ['name', 'surname', 'email', 'jmbg', 'remove'];
  public medicalWorkers: MedicalWorker[] = [];

  
  
  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, private bloodBankService: BloodBankServiceService, 
    private medicalWorkerService: MedicalWorkerService, private dialog: MatDialog) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.bloodBankService.getBloodBankById(this.id).subscribe(data => {
      this.bloodBank = data;
    });
    this.getMedicalWorkers();
  }

  editBank() : void {
    this.bloodBankService.editBloodBank(this.bloodBank).subscribe(data => {
      this.bloodBankService.getBloodBankById(this.id).subscribe(data => {
        this.bloodBank = data;
      });
    })
  }

  getMedicalWorkers() {
    this.medicalWorkerService.getAllByBloodBank(this.id).subscribe(data => {
      this.medicalWorkers = data;
      this.dataSource.data = this.medicalWorkers;
    })
  } 


  addMedicalWorkers() {
    console.log('dawdawd')
    this.dialog.open(AddWorkerToBankDialogComponent, {
      width: '20%',
      data: this.bloodBank
    }).afterClosed().subscribe(res=> {
        this.getMedicalWorkers();
      }
    )
  }

  updateTable() {
    console.log("jeaaaaaaaaaaa");
  }

  removeWorker(medicalWorker: any) {
    medicalWorker.bloodBank = null;
    this.medicalWorkerService.updateMedicalWorker(medicalWorker).subscribe(res=> {
      this.getMedicalWorkers();
    });
    
  }

  publishNews() {
    this.router.navigate(['sendNews/' + this.id]);
  }

}
