import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MedicalWorkerService } from 'src/app/services/medical-worker.service';
import { MedicalWorker } from 'src/app/model/medicalWorker';
import { BloodBank } from 'src/app/model/bloodBank';
@Component({
  selector: 'app-add-worker-to-bank-dialog',
  templateUrl: './add-worker-to-bank-dialog.component.html',
  styleUrls: ['./add-worker-to-bank-dialog.component.css']
})
export class AddWorkerToBankDialogComponent implements OnInit {

  freeMedicalWorkers: any[] = [];
  medicalWorkersForm!: FormGroup;
  selectedWorker!: MedicalWorker;
  @Output() changed = new EventEmitter<void>();
  constructor(private formBuilder: FormBuilder, private DialogRef: MatDialogRef<AddWorkerToBankDialogComponent>,
    private api: MedicalWorkerService, @Inject(MAT_DIALOG_DATA) public data : BloodBank) { }

  ngOnInit(): void {
    this.medicalWorkersForm = this.formBuilder.group({
      selectedMW : [MedicalWorker],
    })
    this.api.getFreeMedicalWorkers().subscribe(res => {
      this.freeMedicalWorkers = res;
    
    })
  }
  
  close() {
    this.DialogRef.close();
  }

  addWorker() {
    this.selectedWorker = this.medicalWorkersForm.value.selectedMW;
    console.log(this.data);
    this.selectedWorker.bloodBank = this.data;
    this.api.updateMedicalWorker(this.selectedWorker).subscribe(res=> {
      this.DialogRef.close();
    }
    );
  }

}
