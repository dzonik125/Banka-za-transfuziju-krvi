import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Message } from 'src/app/model/message';
import { Buffer } from 'buffer';
import { Storage, ref, uploadBytesResumable, getDownloadURL } from '@angular/fire/storage'
import { ActivatedRoute } from '@angular/router';
import { BloodBankServiceService } from 'src/app/services/blood-bank-service.service';

window.Buffer = Buffer;

@Component({
  selector: 'app-send-news',
  templateUrl: './send-news.component.html',
  styleUrls: ['./send-news.component.css']
})
export class SendNewsComponent implements OnInit {
  
  id:any
  apiKey:string = ''
  constructor(private http: HttpClient,
              private storage: Storage,
              private route: ActivatedRoute,
              private bloodBankService: BloodBankServiceService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.bloodBankService.getApiKey(this.id).subscribe(res=> {
      this.apiKey = res;
      if(this.apiKey === null) {
        this.apiKey = '';
      }
    })
  }

  url="";
  subject = '';
  text = '';
  public file: any = {}

  onFileChanged(event: any) {
    if(event.target.files){
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    let byteArray;
    reader.onload = (event: any) => {
       this.url = event.target.result;
    }
  }
  this.file = event.target.files[0];
}




sendNews(downloadURL: any){
  console.log(this.apiKey + 'ddddddddddddddddddddddddddddddddddddddd')
  console.log(downloadURL);
  var msg = new Message(this.text, this.subject, downloadURL, this.apiKey);
  var headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  this.http.post('http://localhost:8081/sendNews', msg, { headers }).subscribe(res => {
    console.log(res);
  });
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
    this.sendNews(downloadURL);
  });

}
);

}

}
