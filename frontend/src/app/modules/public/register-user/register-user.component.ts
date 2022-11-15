import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Donor } from 'src/app/model/donor';
import { Address } from 'src/app/model/address';
import { Validator } from '../../util/validation';
import { UserService } from 'src/app/services/user.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent{

  repeatPass = '';

  public user: Donor = new Donor;
  public address: Address = new Address;
  public validation: Validator = new Validator;


  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router,
              private notifyService : NotificationService) {
  }

  createUser(){
    this.user.address = this.address;
    this.userService.createUser(this.user).subscribe();
    this.notifyService.showSuccess("You have successfully registered", "Success");
  }

}
