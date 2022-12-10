import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map, Observable, shareReplay } from 'rxjs';
import { AuthService } from '../../authentication/services/auth.service';
import { CreateSurveyComponent } from '../create-survey/create-survey.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  isLogged: boolean = false;
  userRole: string = '';

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

  constructor(private breakpointObserver: BreakpointObserver, private authService: AuthService, private router: Router) { }


  ngOnInit(): void {
    this.authService.loginObserver.subscribe((val) => {
      this.isLogged = val;
      if(this.isLogged)
        this.userRole = this.authService.getUserRole();
    });
  }

  logout(): void {
    this.authService.logout();
    this.userRole = '';
  }


}
