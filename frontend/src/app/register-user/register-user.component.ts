import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent{

  repeatPass = '';

  User = {
    name: '',
    surname: '',
    password: '',
    adress: {
      country: '',
      city: '',
      street: '',
      number: undefined
    },
    jmbg: '12345',
    email: '',
    occupation: '',
    gender: ''
  }

  checkEmail(){
    var test = this.User.email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    var reg = document.getElementById('reg') as HTMLInputElement | null;
    var err = document.getElementById('emailErr') as HTMLInputElement | null;
    if(!test) {
      err?.setAttribute("style", "display: block;");
      err?.setAttribute("style", "color: red;");
      reg?.setAttribute("disabled", "disabled");
    } else {
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
    if(this.User.email === ''){
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }

  checkForPassMatch(){
    var err = document.getElementById('err') as HTMLInputElement | null;
    var reg = document.getElementById('reg') as HTMLInputElement | null;
    if(this.repeatPass !== '') {
      if(this.repeatPass !== this.User.password) {
        err?.setAttribute("style", "display: block;");
        err?.setAttribute("style", "color: red;");
        reg?.setAttribute("disabled", "disabled");
      } else {
        err?.setAttribute("style", "display: none;");
        reg?.removeAttribute("disabled");
      }
    } else {
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }



  constructor(private http: HttpClient, private userService: UserService, private router: Router) {
  }

  createUser(){
    window.alert(this.User.name)
    this.userService.createUser(this.User).subscribe();

  }



}
