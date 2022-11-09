import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/model/address';
import { BloodBank } from 'src/app/model/bloodBank';
import { Validator } from '../../util/validation';
import { BloodBankServiceService } from '../../services/blood-bank-service.service';

@Component({
  selector: 'app-register-blood-bank',
  templateUrl: './register-blood-bank.component.html',
  styleUrls: ['./register-blood-bank.component.css']
})
export class RegisterBloodBankComponent {

  repeatPass = '';

  public bloodBank: BloodBank = new BloodBank;
  public address: Address = new Address;
  public validation: Validator = new Validator;


  constructor(private http: HttpClient, private bbservice: BloodBankServiceService, private router: Router) {
  }

  createBloodBank(){
    window.alert(this.bloodBank.name)
    window.alert(this.bloodBank.description)

    this.bloodBank.address = this.address;
    this.bbservice.createBloodBank(this.bloodBank).subscribe();
  }
}
