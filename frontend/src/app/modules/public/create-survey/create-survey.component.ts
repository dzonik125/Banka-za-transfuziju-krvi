import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Survey } from 'src/app/model/survey';
import { NotificationService } from 'src/app/services/notification.service';
import { SurveyService } from 'src/app/services/survey.service';

@Component({
  selector: 'app-create-survey',
  templateUrl: './create-survey.component.html',
  styleUrls: ['./create-survey.component.css']
})
export class CreateSurveyComponent implements OnInit {

  public survey: Survey = new Survey;

  surveyForm!: FormGroup;

  constructor(private surveyService: SurveyService,
              private notifyService : NotificationService
              ) {   }

  ngOnInit(): void {
  }

  createSurvey(){
    if(this.survey.answer3 == 'yes' || this.survey.answer5 == 'yes' || this.survey.answer6 == 'yes'){
      this.notifyService.showError("You cannot donate blood", "Warning");
    }
    else{
    this.surveyService.createSurvey(this.survey).subscribe();
    this.notifyService.showSuccess("You have successfully completed the survey", "Success");
    }
  }


}
