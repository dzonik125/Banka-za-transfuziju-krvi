import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { JwtHelperService } from '@auth0/angular-jwt';
import { thisMonth } from '@igniteui/material-icons-extended';
import { reload, User } from 'firebase/auth';
import { Survey } from 'src/app/model/survey';
import { NotificationService } from 'src/app/services/notification.service';
import { SurveyService } from 'src/app/services/survey.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-survey',
  templateUrl: './create-survey.component.html',
  styleUrls: ['./create-survey.component.css']
})
export class CreateSurveyComponent implements OnInit {

  public survey: Survey = new Survey;

  surveyForm!: FormGroup;

  userClaims: any;

  user: any;
  document: any;

  constructor(private surveyService: SurveyService,
              private notifyService : NotificationService,
              private jwtHelper: JwtHelperService,
              private userService: UserService
              ) {   }

  ngOnInit(): void {
    this.userService.fetchUser(this.jwtHelper.decodeToken().id).subscribe(res => {
      this.user = res;

  })
  }

  createSurvey(){
     if(this.user.hasSurvey){
        this.userClaims = this.jwtHelper.decodeToken();
        this.survey.donor = this.userClaims.id;
        this.surveyService.createSurvey(this.survey).subscribe();
        this.notifyService.showSuccess("You have successfully updated the survey!", "Update");
      } else{
        this.userClaims = this.jwtHelper.decodeToken();
        this.survey.donor = this.userClaims.id;
        this.surveyService.createSurvey(this.survey).subscribe();
        this.notifyService.showSuccess("You have successfully completed the survey", "Success");
    }

  }


}
