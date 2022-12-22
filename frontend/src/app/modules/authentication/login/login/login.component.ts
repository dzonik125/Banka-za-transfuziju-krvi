import { AuthService } from '../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Credentials } from '../../model/credentials.model';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AdminService } from 'src/app/services/admin.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credentials = new Credentials();
  public error: boolean = false;
  public admin: any;

  constructor(private authService: AuthService, private router: Router, private jwtHelper: JwtHelperService, private adminService: AdminService) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login(this.credentials).subscribe(res => {

        if('ROLE_ADMIN' === this.jwtHelper.decodeToken().role) 
        {
          this.adminService.getAdministratorById(this.jwtHelper.decodeToken().id).subscribe(res => {
            this.admin = res;

            if(this.admin.firstLogin) {
              this.router.navigate(['/changepassword']);
            }
          })

          
        }


      this.router.navigate(['/']);
    },
    error => this.error = true);

    
  }

}
