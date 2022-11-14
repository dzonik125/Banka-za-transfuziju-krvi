import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BloodBank } from 'src/app/model/bloodBank';
import { BloodBankServiceService } from 'src/app/services/blood-bank-service.service';

@Component({
  selector: 'app-blood-bank-view',
  templateUrl: './blood-bank-view.component.html',
  styleUrls: ['./blood-bank-view.component.css']
})
export class BloodBankViewComponent implements OnInit {

  id: any;
  bloodBank!: BloodBank;
  constructor(private route: ActivatedRoute, private http: HttpClient, private userService: BloodBankServiceService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.userService.getBloodBankById(this.id).subscribe(data => {
      this.bloodBank = data;
    });
  }

}
