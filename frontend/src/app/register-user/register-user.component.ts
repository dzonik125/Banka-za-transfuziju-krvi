import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  repeatPass = '';

  User = {
    Name: '',
    Surname: '',
    Username: '',
    Password: '',
    Adress: {
      Country: '',
      City: '',
      Street: '',
      Number: undefined
    },
    JMBG: undefined,
    Email: '',
    Occupation: '',
    Gender: ''
  }
  
  checkEmail(){
    var test = this.User.Email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
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
    if(this.User.Email === ''){
      err?.setAttribute("style", "display: none;");
      reg?.removeAttribute("disabled");
    }
  }

  checkForPassMatch(){
    var err = document.getElementById('err') as HTMLInputElement | null;
    var reg = document.getElementById('reg') as HTMLInputElement | null;
    if(this.repeatPass !== '') {
      if(this.repeatPass !== this.User.Password) {
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

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

}
