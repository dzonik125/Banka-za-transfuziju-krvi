import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-save-api-key',
  templateUrl: './save-api-key.component.html',
  styleUrls: ['./save-api-key.component.css']
})
export class SaveApiKeyComponent implements OnInit {

  ApiKey = '';
  id: any;
  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  sendData(){
    console.log(this.ApiKey);
    console.log(this.id);
    const body = {
      id: this.id,
      api: this.ApiKey
    }
    this.http.post('http://localhost:8081/bloodBanks/addApi/', body).subscribe();
  }

}
