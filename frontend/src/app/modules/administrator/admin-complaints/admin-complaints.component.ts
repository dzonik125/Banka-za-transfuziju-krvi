import { Component, OnInit } from '@angular/core';
import { ComplaintsService } from 'src/app/services/complaints.service';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { ComplaintAnswerComponent } from '../complaint-answer/complaint-answer.component';


@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  public comments: any = [ {
    name: 'marko',
    email: 'mail@mail.com',
    description: 'This sucks!'
  },
  {
    name: 'milos',
    email: 'milos@mail.com',
    description: 'Not too bad!'
  }
  ];

  public complaints: any[] = [];


  constructor(private service: ComplaintsService, public dialog: MatDialog) { }


  //name surname description username
  ngOnInit(): void {
    this.service.findAllUnanswered().subscribe(res => {
      this.complaints = res;
      
    })
  }


  answerComplaint(complaint: any) {
    const dialogRef = this.dialog.open(ComplaintAnswerComponent, {
      data: {complaint: complaint},
      width: '900px',
      height: '600px'

    });

    dialogRef.afterClosed().subscribe(() => {
      // Do stuff after the dialog has closed
      location.reload();
    });
  }

  trackByFn(index: any, item: any) {
    return index; // or item.id
  }

  
}
