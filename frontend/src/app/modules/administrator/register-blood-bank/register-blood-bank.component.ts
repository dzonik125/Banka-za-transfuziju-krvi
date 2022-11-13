import { HttpClient } from '@angular/common/http';
import { Component, Input, NgZone, OnInit } from '@angular/core';
import { Address } from 'src/app/model/address';
import { BloodBank } from 'src/app/model/bloodBank';
import { Validator } from '../../util/validation';
import { Router } from '@angular/router';
import { Storage, ref, uploadBytesResumable, getDownloadURL } from '@angular/fire/storage'
import { BloodBankServiceService } from 'src/app/services/blood-bank-service.service';
import { MedicalWorkerService } from 'src/app/services/medical-worker.service';
import { MatDialog } from '@angular/material/dialog';
import { RegisterMedicalWorkerComponent } from '../register-medical-worker/register-medical-worker.component';

@Component({
  selector: 'app-register-blood-bank',
  templateUrl: './register-blood-bank.component.html',
  styleUrls: ['./register-blood-bank.component.css']
})
export class RegisterBloodBankComponent implements OnInit {

  repeatPass = '';

  public bloodBank: BloodBank = new BloodBank;
  public address: Address = new Address;
  public validation: Validator = new Validator;
  public medicalWorkers: any[] = [];

  path!:String;

  public file: any = {}
  public selectedMW: any;

  constructor(private http: HttpClient,
              private bbservice: BloodBankServiceService,
              private router: Router,
              private storage: Storage,
              private medicalWorkerService: MedicalWorkerService,
              public dialog: MatDialog
              ) {
              }                

    
          
    ngOnInit(): void {
      this.medicalWorkerService.getFreeMedicalWorkers().subscribe(res => {
        this.medicalWorkers = res;
      
    })  
  }  

  

  url: any = '';

  onFileChanged(event: any) {
    if(event.target.files){
    var reader = new FileReader();  
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event: any) => {
        this.url = event.target.result;
    }    
  }  
  this.file = event.target.files[0];
  }



  public uploadUrl: any;

  createBloodBank(downloadURL: any){
    this.bloodBank.address = this.address;
    this.bloodBank.image = downloadURL;
    this.bloodBank.medicalWorker = this.selectedMW;
    this.bbservice.createBloodBank(this.bloodBank).subscribe();
  }  


  public  uploadImage(){
    const storageRef = ref(this.storage, this.file.name);
    const uploadTask = uploadBytesResumable(storageRef, this.file);
    uploadTask.on('state_changed',
  (snapshot) => {
    const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
    console.log('Upload is ' + progress + '% done');
  },
  (error) => {
  },
  () => {
      getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
      console.log('File available at', downloadURL);
      this.createBloodBank(downloadURL);
    });

  }
);

  }
}
