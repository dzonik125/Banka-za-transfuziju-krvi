import { LiveAnnouncer } from '@angular/cdk/a11y';
import { DOCUMENT } from '@angular/common';
import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
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
export class DisplayAllUsersComponent implements AfterViewInit {
  
  public dataSource = new MatTableDataSource<BloodBank>();
  public medicalWorkers: any[] = [];
  public donors: any[] = [];
  public admins: any[] = [];

  constructor(private medicalWorkerServi: MedicalWorkerService, private adminService: AdminService ,private donorService: UserService, private router: Router, private _liveAnnouncer: LiveAnnouncer,
    @Inject(DOCUMENT) document: Document) { }


  ngAfterViewInit() {
    this.medicalWorkerServi.getMedicalWorkers().subscribe(res => {
      this.medicalWorkers = res;
      
    })

    this.donorService.getDonors().subscribe(res => {
      this.donors = res;
      
    })

    this.adminService.getAdmins().subscribe(res => {
      this.admins = res;
      
    })

  }

  clickedDonnors() {
    // var btn = document.getElementById('donnorButt') as HTMLElement;
    // btn.className="btn btn-primary";

    // var adminBut = document.getElementById('adminButt') as HTMLElement;
    // adminBut.className="btn btn-outline-primary ml-3  border";

    // var medWorkButt = document.getElementById('medWorkButt') as HTMLElement;
    // medWorkButt.className="btn btn-outline-primary ml-3  border";

  }
  clickedMedicalWorkers(){
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
