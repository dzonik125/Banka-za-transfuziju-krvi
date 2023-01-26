import { LiveAnnouncer } from '@angular/cdk/a11y';
import { DOCUMENT } from '@angular/common';
import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { BloodBank } from 'src/app/model/bloodBank';
import { AdminService } from 'src/app/services/admin.service';
import { UserService } from 'src/app/services/user.service';
import { MedicalWorkerService } from '../../../services/medical-worker.service';



@Component({
  selector: 'app-display-all-users',
  templateUrl: './display-all-users.component.html',
  styleUrls: ['./display-all-users.component.css']
})
export class DisplayAllUsersComponent implements AfterViewInit, OnInit {
  
  public dataSource = new MatTableDataSource<BloodBank>();
  public medicalWorkers: any[] = [];
  public donors: any[] = [];
  public admins: any[] = [];
  public filteredString: string ='';


  currentPage = 1;
  itemsPerPage = 6;

  onPageChange(event: any) {
    this.currentPage = event;
  }

  ngOnInit() {
    this.medicalWorkerServi.getMedicalWorkers().subscribe(res => {
      this.medicalWorkers = res;
      
    })
  }

  constructor(private medicalWorkerServi: MedicalWorkerService, private adminService: AdminService ,private donorService: UserService, private router: Router, private _liveAnnouncer: LiveAnnouncer,
    @Inject(DOCUMENT) document: Document) {
      
     }


  ngAfterViewInit() {


    this.donorService.getDonors().subscribe(res => {
      this.donors = res;
      
    })

    this.adminService.getAdmins().subscribe(res => {
      this.admins = res;
      
    })

  }

  clickedDonnors() {
    this.currentPage = 1;
    // var btn = document.getElementById('donnorButt') as HTMLElement;
    // btn.="btn btn-primary ml-3 me-3 border";

    // var adminBut = document.getElementById('adminButt') as HTMLElement;
    // adminBut.className="btn btn-outline-primary ml-3  border";

    // var medWorkButt = document.getElementById('medWorkButt') as HTMLElement;
    // medWorkButt.className="btn btn-outline-primary ml-3  border";

  }
  clickedMedicalWorkers(){
    this.currentPage = 1;
    // var btn = document.getElementById('medWorkButt') as HTMLElement;
    // btn.className="btn btn-primary";

    // var adminBut = document.getElementById('adminButt') as HTMLElement;
    // adminBut.className="btn btn-outline-primary ml-3  border";

    // var donnorButt = document.getElementById('donnorButt') as HTMLElement;
    // donnorButt.className="btn btn-outline-primary ml-3  border";

  }

  showDiv = {
    donorsBool : true,
    medicalWorkersBool : false,
    adminsBool : false
  }

  
}
