import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map, Observable, shareReplay } from 'rxjs';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from '../../authentication/services/auth.service';
import { CreateSurveyComponent } from '../create-survey/create-survey.component';
import { MedicalWorkerService } from 'src/app/services/medical-worker.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  isLogged: boolean = false;
  userRole: string = '';
  admin: any;
  firstLogin: boolean = false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

  constructor(private breakpointObserver: BreakpointObserver, private authService: AuthService, private router: Router, 
    private adminService: AdminService, private jwtHelper: JwtHelperService,
    private medicalWorkerService: MedicalWorkerService) { }
  

  ngOnInit(): void {
    this.authService.loginObserver.subscribe((val) => {
      this.isLogged = val;
      if(this.isLogged)
        this.userRole = this.authService.getUserRole();
    });

    if(this.userRole == 'ROLE_ADMIN')
    {
      this.adminService.getAdministratorById(this.jwtHelper.decodeToken().id).subscribe(res => {
        this.admin = res;
      })
      
    }
  }

  showMyBank() {
    this.medicalWorkerService.getBloodBankId(this.jwtHelper.decodeToken().id).subscribe(res=>{
      this.router.navigate(['/bloodBank/' + res])
    })
  }

  logout(): void {
    console.log(this.admin)
    this.authService.logout();
    this.userRole = '';
    this.router.navigate(['/']);
  }


}
