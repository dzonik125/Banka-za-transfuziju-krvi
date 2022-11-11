import { User } from 'src/app/model/user';
import { Survey } from './../../../model/survey';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SurveyService } from './service/survey.service';
import { FormBuilder, FormGroup } from '@angular/forms';



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
