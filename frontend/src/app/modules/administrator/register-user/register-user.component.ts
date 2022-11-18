import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Donor } from 'src/app/model/donor';
import { Address } from 'src/app/model/address';
import { Validator } from '../../util/validation';
import { UserService } from 'src/app/services/user.service';
import { NotificationService } from 'src/app/services/notification.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent{

  repeatPass = '';
  form!: FormGroup;

  public user: Donor = new Donor;
  public address: Address = new Address;
  public validation: Validator = new Validator;

  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router,
              private notifyService : NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(){
  }

  public registerForm = new FormGroup({
    name: new FormControl(""),
    surname: new FormControl(""),
    email: new FormControl(""),
    password: new FormControl(""),
    rpass: new FormControl(""),
    jmbg: new FormControl(""),
    country: new FormControl(""),
    city: new FormControl(""),
    number: new FormControl(""),
    street: new FormControl(""),
    occupation: new FormControl(""),
    gender: new FormControl(""),
  })

  createUser(){
    this.user.address = this.address;
    this.userService.createUser(this.user).subscribe(res =>{
      this.notifyService.showSuccess("You have successfully registered", "Success");
      this.user.name = '';
      this.user.surname = '';
      this.user.email = '';
      this.user.password = '';
      this.user.jmbg = '';
      this.user.address.country = '';
      this.user.address.city = '';
      this.user.address.number = '';
      this.user.address.street = '';
      this.user.occupation = '';
      this.user.gender = '';
      this.validation.repeatPass = '';
    }, error => {
      this.notifyService.showError(error.error, "Error");
    })

  }

}
