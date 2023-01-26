import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Address } from 'src/app/model/address';
import { MedicalWorker } from 'src/app/model/medicalWorker';
import { MedicalWorkerService } from '../../../services/medical-worker.service';
import { Validator } from '../../util/validation';

@Component({
  selector: 'app-register-medical-worker',
  templateUrl: './register-medical-worker.component.html',
  styleUrls: ['./register-medical-worker.component.css']
})
export class RegisterMedicalWorkerComponent{

  public user: MedicalWorker = new MedicalWorker;
  public validation: Validator = new Validator;
  public address: Address = new Address;
  
  constructor(private http: HttpClient, private medicalServ: MedicalWorkerService, private router: Router, private tost: ToastrService) {
  }


  createMedicalWorker(): void {
    this.user.address = this.address;
    this.medicalServ.createMedicalWorker(this.user).subscribe(res => {
      this.tost.success("success", "Sucess registration!")
      this.router.navigate(['/displayAllUsers']);
    });
  }

}
