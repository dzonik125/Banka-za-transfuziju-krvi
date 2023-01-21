import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IDropdownSettings, } from 'ng-multiselect-dropdown';
import { ToastrService } from 'ngx-toastr';
import { BloodBankServiceService } from 'src/app/services/blood-bank-service.service';
import { MedicalExaminationService } from 'src/app/services/medical-examination.service';

@Component({
  selector: 'app-medical-examination',
  templateUrl: './medical-examination.component.html',
  styleUrls: ['./medical-examination.component.css']
})

export class MedicalExaminationComponent implements OnInit {

  amount: any =0;
  bloodType: any ='';
 email: any = '';
  bb: any = '';
  bloodBanks: any[] = [];

  medicalExaminationDTO: any = {
    amount: Int8Array,
    bloodType: String,
    email: String,
    bb: String

  };
  dropdownSettings:IDropdownSettings={};

  constructor(private bloodBanksService: BloodBankServiceService, private medicalExaminationService: MedicalExaminationService, private tost: ToastrService, private router: Router) { }

  ngOnInit(): void {
  
    this.bloodBanksService.getBloodBanks().subscribe(res => {
      this.bloodBanks = res;
    
    })

    this.dropdownSettings = {
      idField: 'id',
      textField: 'name',
      singleSelection: true,
      allowSearchFilter: true,
      enableCheckAll: false,
      noDataAvailablePlaceholderText: "There is no free managers availabale to show",
    };

  } 

  createME() {
    this.medicalExaminationDTO.amount = this.amount;
    this.medicalExaminationDTO.bloodType = this.bloodType;
    this.medicalExaminationDTO.email = this.email;
    this.medicalExaminationDTO.bb = this.bb;
    
    this.medicalExaminationService.createMedicalExamination(this.medicalExaminationDTO).subscribe(res => {
      this.tost.success("success", "Medical examination saved!")
      this.router.navigate(['/']);
    });
  }

  onItemSelect(item: any) {
    this.bb = item.name;
  }

}
