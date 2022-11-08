import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Message } from 'src/app/model/message';
import { Buffer } from 'buffer';

window.Buffer = Buffer;

@Component({
  selector: 'app-send-news',
  templateUrl: './send-news.component.html',
  styleUrls: ['./send-news.component.css']
})
export class SendNewsComponent implements OnInit {
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  url="";
  subject = '';
  text = '';

  onFileChanged(event: any) {
    if(event.target.files){
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    let byteArray;
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

sendNews(){
  var base64string = Buffer.from(this.url).toString('base64');
  var msg = new Message(this.text, this.subject, base64string);
  var headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  this.http.post('http://localhost:8081/sendNews', msg, { headers }).subscribe(res => {
    console.log(res);
  });
}

}
