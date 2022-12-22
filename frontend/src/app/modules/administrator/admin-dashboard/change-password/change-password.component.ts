import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Validator } from 'src/app/modules/util/validation';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public admin: any;
  public validation: Validator = new Validator;
  public allertMewssage: any = '';
  public repeatPass: any = "";
  public pass: any ="";

  constructor(private adminService: AdminService, private jwtHelper: JwtHelperService, private router: Router) { }

  ngOnInit(): void {
    this.adminService.getAdministratorById(this.jwtHelper.decodeToken().id).subscribe(res => {
      this.admin = res;
    })
  }

  chan() {

    if(this.repeatPass !== this.pass)
      this.allertMewssage= "Password mismatch!"
    else
      this.allertMewssage = " "
  }

  setPassword() {

    if(this.repeatPass === this.pass)
    {
      this.admin.password = this.pass;
      this.adminService.updateAdmin(this.admin).subscribe(res => {
        this.router.navigate(['/']);
      })
    }

  }


}
