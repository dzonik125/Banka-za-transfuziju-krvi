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
import { IDropdownSettings, } from 'ng-multiselect-dropdown';


@Component({
  selector: 'app-register-blood-bank',
  templateUrl: './register-blood-bank.component.html',
  styleUrls: ['./register-blood-bank.component.css']
})

export class RegisterBloodBankComponent implements OnInit {
  dropdownList: any[] =[];
  dropdownSettings:IDropdownSettings={};
  selected: any[] = [];
  
  public bloodBank: BloodBank = new BloodBank;
  public address: Address = new Address;
  public validation: Validator = new Validator;
  public freeMedicalWorkers: any[] = [];
  public selectedMW: any[] =[];
  
  path!:String;
  public file: any = {}
  url: any = '';
  public uploadUrl: any;


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
      this.freeMedicalWorkers = res;
    
    })

    this.dropdownSettings = {
      idField: 'id',
      textField: 'fullname',
      allowSearchFilter: true,
      enableCheckAll: false,
      noDataAvailablePlaceholderText: "There is no free managers availabale to show",
    };
  }  
    
  onItemSelect(item: any) {
    
    var selectedMW = this.freeMedicalWorkers.find(x => x.id === item.id);
    this.selectedMW.push(selectedMW);
    
  }

  onItemDeselect(item: any) {
    const indexOfObject = this.selectedMW.findIndex(object => {
      return object.id === item.id;
    });
          
    this.selectedMW.splice(indexOfObject, 1);
    
  }
    
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
  
  createBloodBank(downloadURL: any){
    
    this.bloodBank.address = this.address;
    this.bloodBank.image = downloadURL;
    this.bloodBank.medicalWorkers = this.selectedMW;
    this.bbservice.createBloodBank(this.bloodBank).subscribe();   
  
  }  

}
