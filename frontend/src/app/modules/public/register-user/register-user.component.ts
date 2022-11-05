import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from './service/user.service';
import { User } from 'src/app/model/user';
import { Address } from 'src/app/model/address';
import { Validator } from '../../util/validation';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent{

  repeatPass = '';

  public user: User = new User;
  public address: Address = new Address;
  public validation: Validator = new Validator;


  constructor(private http: HttpClient, private userService: UserService, private router: Router) {
  }

  createUser(){
    this.user.address = this.address;
    this.userService.createUser(this.user).subscribe();
  }

}
