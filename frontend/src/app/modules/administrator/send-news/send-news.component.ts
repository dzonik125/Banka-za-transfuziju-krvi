import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-send-news',
  templateUrl: './send-news.component.html',
  styleUrls: ['./send-news.component.css']
})
export class SendNewsComponent implements OnInit {

  subject: any;
  message: any;
  text: any;


  constructor() { }

  ngOnInit(): void {
  }
  url="./assets/bb.png";

  onFileChanged(event: any) {
    if(event.target.files){
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event: any) => {
       this.url = event.target.result;
    }
  }
}

}
