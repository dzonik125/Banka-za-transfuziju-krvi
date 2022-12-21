import { Component, OnInit } from '@angular/core';
import { ComplaintsService } from 'src/app/services/complaints.service';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  public comments: any = [ {
    name: 'marko',
    email: 'mail@mail.com',
    description: 'This sucks!'
  },
  {
    name: 'milos',
    email: 'milos@mail.com',
    description: 'Not too bad!'
  }
  ];

  public complaints: any[] = [];


  constructor(private service: ComplaintsService) { }


  
  ngOnInit(): void {
    this.service.getComplaints().subscribe(res => {
      this.complaints = res;
      
      window.alert(JSON.stringify(res[0]))
    })
  }

}
