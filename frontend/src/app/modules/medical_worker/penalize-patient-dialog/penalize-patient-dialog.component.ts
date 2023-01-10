import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-penalize-patient-dialog',
  templateUrl: './penalize-patient-dialog.component.html',
  styleUrls: ['./penalize-patient-dialog.component.css']
})
export class PenalizePatientDialogComponent implements OnInit {

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
