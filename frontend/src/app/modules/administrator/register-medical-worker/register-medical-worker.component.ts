import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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


  constructor(private http: HttpClient, private medicalServ: MedicalWorkerService, private router: Router) {
  }


  createMedicalWorker(): void {
    this.medicalServ.createMedicalWorker(this.user).subscribe();
  }

}
