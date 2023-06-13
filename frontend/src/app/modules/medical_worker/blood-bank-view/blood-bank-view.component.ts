import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
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
import * as L from 'leaflet';
import { BloodBankBlood } from 'src/app/model/bloodBankBlood';
import { Item } from 'src/app/model/item';
import { ItemService } from 'src/app/services/item.service';
import { JwtHelperService } from '@auth0/angular-jwt';
@Component({
  selector: 'app-blood-bank-view',
  templateUrl: './blood-bank-view.component.html',
  styleUrls: ['./blood-bank-view.component.css']
})
export class BloodBankViewComponent implements OnInit  {

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
  public dataSourceBlood = new MatTableDataSource<BloodBankBlood>();
  public displayedColumns = ['name', 'surname', 'email', 'jmbg', 'remove'];
  public displayedColumnsBlood = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'];
  public medicalWorkers: MedicalWorker[] = [];
  public bloodBankBlood: BloodBankBlood = new BloodBankBlood();
  public items!: Item[];
  public dataSourceItems = new MatTableDataSource<Item>();
  public dataSourceUsedItems = new MatTableDataSource<Item>();
  public displayedColumnsItems = ['name', 'quantity'];
  public showContent!: boolean;

  private map!: L.Map;

  private initMap(): void {
    this.map = L.map('map', {
      center: [ 39.8282, -98.5795 ],
      zoom: 3
    });
    this.map.on('dblclick', (event) => {
      const latLng = event.latlng;
      console.log('Clicked coordinates:', latLng.lat, latLng.lng);
    });
  }
  
  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, private bloodBankService: BloodBankServiceService, 
    private medicalWorkerService: MedicalWorkerService, private dialog: MatDialog, private itemService : ItemService,
    private jwtHelper : JwtHelperService) { }


    ngOnInit(): void {
      this.id = this.route.snapshot.paramMap.get('id');
      this.medicalWorkerService.getBloodBankId(this.jwtHelper.decodeToken().id).subscribe(res=> {
        this.showContent = this.id == res;
        console.log(this.showContent);
      })
      this.initMap();
      const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 14,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
    
    this.bloodBankService.getBloodBankById(this.id).subscribe(data => {
      this.bloodBank = data;
    });
    this.getMedicalWorkers();
    this.bloodBankService.getBlood(this.id).subscribe(res=> {
      this.bloodBankBlood = res;
      console.log(res);
      this.dataSourceBlood.data = [this.bloodBankBlood];
    })

    this.itemService.getByBloodBankId(this.id).subscribe(
      res=> {
        this.items = res;
        this.dataSourceItems.data = this.items
      }
    )
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
