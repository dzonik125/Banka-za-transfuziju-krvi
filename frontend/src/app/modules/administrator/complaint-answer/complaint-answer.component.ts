import { Component, Inject, NgZone, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ComplaintsService } from 'src/app/services/complaints.service';

@Component({
  selector: 'app-complaint-answer',
  templateUrl: './complaint-answer.component.html',
  styleUrls: ['./complaint-answer.component.css']
})
export class ComplaintAnswerComponent implements OnInit {

  public complainta: any;
  public answer: string = "ss";

  constructor(    public dialogRef: MatDialogRef<ComplaintAnswerComponent>,
    @Inject(MAT_DIALOG_DATA) public complaint: any, public service: ComplaintsService, private ngZone: NgZone) {
     }
    
    
    ngOnInit(): void {
    }

    sendMail() {
      const input = document.getElementById('textAreaExample') as HTMLInputElement | null;

      this.complaint.answer = input?.value

      this.service.editComplaint(this.complaint.complaint, input?.value).subscribe(res => {
        
      })
      this.dialogRef.close(true);
    }

}
