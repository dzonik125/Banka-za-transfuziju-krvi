import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Survey } from 'src/app/model/survey';
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
              private dialogRef: MatDialog,
              ) {   }

  ngOnInit(): void {
  }

  createSurvey(){
    this.surveyService.createSurvey(this.survey).subscribe();
    this.dialogRef.closeAll();

  }


}
