import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/model/address';
import { Administrator } from 'src/app/model/administrator';
import { AdminService } from 'src/app/services/admin.service';
import { Validator } from '../../util/validation';

@Component({
  selector: 'app-register-administrator',
  templateUrl: './register-administrator.component.html',
  styleUrls: ['./register-administrator.component.css']
})
export class RegisterAdministratorComponent implements OnInit {

  public user: Administrator =  new Administrator;
  public validation: Validator = new Validator;
  public address: Address = new Address;
  
  constructor(private http: HttpClient,  private router: Router, private adminservice: AdminService) {
  }

  createAdministrator(): void {
    this.user.address = this.address;
    this.user.firstLogin = true;
    this.adminservice.createAdministrator(this.user).subscribe();
  }

  
  ngOnInit(): void {
  }

}
