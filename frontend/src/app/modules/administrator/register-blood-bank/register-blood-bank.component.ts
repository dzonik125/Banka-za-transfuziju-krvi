import { HttpClient } from '@angular/common/http';
import { Component, Input, NgZone, OnInit } from '@angular/core';
import { Address } from 'src/app/model/address';
import { BloodBank } from 'src/app/model/bloodBank';
import { Validator } from '../../util/validation';
import { BloodBankServiceService } from '../../services/blood-bank-service.service';
import { Buffer } from 'buffer';
import { Router } from '@angular/router';
import { Storage, ref, uploadBytesResumable, getDownloadURL } from '@angular/fire/storage'
import { delay } from 'rxjs';
import { waitForAsync } from '@angular/core/testing';

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

  path!:String;

  public file: any = {}




  constructor(private http: HttpClient,
              private bbservice: BloodBankServiceService,
              private router: Router,
              private storage: Storage
              ) {
  }
  ngOnInit(): void {
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
}


convertDataURIToBinary(dataURI: string): Uint8Array {
  var base64Index = dataURI.indexOf(';base64,') + ';base64,'.length;
  var base64 = dataURI.substring(base64Index);
  var raw = window.atob(base64);
  var rawLength = raw.length;
  var array = new Uint8Array(new ArrayBuffer(rawLength));

  for(let i = 0; i < rawLength; i++) {
    array[i] = raw.charCodeAt(i);
  }
  return array;
}

upload(event: any){
  this.file = event.target.files[0];
}

public uploadUrl: any;

   createBloodBank(downloadURL: any){
    this.bloodBank.address = this.address;
    this.bloodBank.image = downloadURL;
    console.log(downloadURL);
    this.bbservice.createBloodBank(this.bloodBank).subscribe();
  }



  public  uploadImage(){
    const storageRef = ref(this.storage, this.file.name);
    const uploadTask = uploadBytesResumable(storageRef, this.file);
    uploadTask.on('state_changed',
  (snapshot) => {
    // Observe state change events such as progress, pause, and resume
    // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
    const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
    console.log('Upload is ' + progress + '% done');
  },
  (error) => {
    // Handle unsuccessful uploads
  },
  () => {
    // Handle successful uploads on complete
    // For instance, get the download URL: https://firebasestorage.googleapis.com/...
      getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
      console.log('File available at', downloadURL);
      this.createBloodBank(downloadURL);
    });

  }
);

  }
}
