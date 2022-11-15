import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { JsonPipe } from '@angular/common';
import { UserDTO } from 'src/app/model/userDTO';


@Component({
  selector: 'app-user-profile-view',
  templateUrl: './user-profile-view.component.html',
  styleUrls: ['./user-profile-view.component.css']
})
export class UserProfileViewComponent implements OnInit {
  
  trigger: number = 0;
  id: any;
  user: User | undefined;
  oldUser: User | undefined;
  constructor(private route: ActivatedRoute, private http: HttpClient, private userService: UserService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.userService.fetchUser(this.id).subscribe(data => {
      this.user = new User(data);
      console.log(data);
    });
  }

  
  showInputs(): void {
    this.oldUser = new User(this.user);
    var pass = <HTMLInputElement> document.querySelector('#pass');
    if(pass && this.user) pass.value = this.user.password;
    var jmbg = <HTMLInputElement> document.querySelector('#jmbg');
    if(jmbg && this.user) jmbg.value = this.user.jmbg;
    var occupation = <HTMLInputElement> document.querySelector('#occupation');
    if(occupation && this.user) occupation.value = this.user.occupation;
    var gender = <HTMLSelectElement> document.querySelector('#selGen');
    if(gender && this.user) gender.value = this.user.gender;
    var ps = document.getElementsByName('toGet');
    var inputs = Array.from(document.getElementsByTagName('input'));
    var b = document.getElementsByName('b');
    b[0].setAttribute('style', 'display: none;');
    b[1].setAttribute('style', 'display: inline-block;');
    b[1].setAttribute('style', 'margin-right: 1rem');
    b[2].setAttribute('style', 'display: inline-block;');
    ps.forEach(element => {
      element.setAttribute('style', 'display: none;');
    });
    inputs.forEach(element => {
      element.classList.add('input-open');
    });
    var select = document.querySelector('#selGen');
    select?.classList.add('input-open');
  }

  hideInputs(): void {
    this.user?.EditUser(this.oldUser);
    var ps = document.getElementsByName('toGet');
    var inputs = Array.from(document.getElementsByTagName('input'));
    var b = document.getElementsByName('b');
    b[0].setAttribute('style', 'display: block;');
    b[1].setAttribute('style', 'display: none;');
    b[2].setAttribute('style', 'display: none;');
    ps.forEach(element => {
      element.setAttribute('style', 'display: block;');
    });
    inputs.forEach(element => {
      element.classList.remove('input-open');
    });
    var select = document.querySelector('#selGen');
    select?.classList.remove('input-open');
  }

  openNameModal(): void {
    const modal: any = document.getElementById('nameModal');
    const inp: any = document.getElementById('nameInp');
    inp.blur();
    modal.showModal();
  }

  confirmNameModal(): void {
    const modal: any = document.getElementById('nameModal');
    var inpName = <HTMLInputElement>document.getElementById('inpNameModal');
    var inpSurname = <HTMLInputElement>document.querySelector('#inpSurnameModal');
    if(this.user){
      this.user.name = inpName.value;
      this.user.surname = inpSurname.value;
    }
    modal.close();
  }

  closeNameModal(): void {
    const modal: any = document.getElementById('nameModal');
    var inpName = <HTMLInputElement>document.getElementById('inpNameModal');
    var inpSurname = <HTMLInputElement>document.querySelector('#inpSurnameModal');
    if(this.user?.name) inpName.value = this.user.name;
    if(this.user?.surname) inpSurname.value = this.user.surname;
    modal.close();
  }

  openAdressModal(): void {
    const modal: any = document.getElementById('adressModal');
    const inp: any = document.getElementById('adressInp');
    inp.blur();
    modal.showModal();
  }

  closeAdressModal(): void {
    const modal: any = document.getElementById('adressModal');
    const inpCountry = <HTMLInputElement> document.querySelector('#coi');
    const inpCity = <HTMLInputElement> document.querySelector('#cii');
    const inpStreet = <HTMLInputElement> document.querySelector('#si');
    const inpNumber = <HTMLInputElement> document.querySelector('#ni');
    if(this.user?.address.country)  inpCountry.value = this.user.address.country;
    if(this.user?.address.city) inpCity.value = this.user.address.city;
    if(this.user?.address.street) inpStreet.value = this.user.address.street;
    if(this.user?.address.number) inpNumber.value = this.user.address.number; 
    modal.close();
  }

  confirmAdressModal(): void {
    const modal: any = document.getElementById('adressModal');
    const inpCountry = <HTMLInputElement> document.querySelector('#coi');
    const inpCity = <HTMLInputElement> document.querySelector('#cii');
    const inpStreet = <HTMLInputElement> document.querySelector('#si');
    const inpNumber = <HTMLInputElement> document.querySelector('#ni');
    if(this.user?.address.country)  this.user.address.country = inpCountry.value;
    if(this.user?.address.city) this.user.address.city = inpCity.value;
    if(this.user?.address.street) this.user.address.street = inpStreet.value;
    if(this.user?.address.number) this.user.address.number  = inpNumber.value;
    modal.close(); 
  }

  value: string = "";

  resetValue(): void {
    this.trigger++;
  }

  confirmEdit(): void {
    if(this.user) this.user.id = this.id;
    var pass = <HTMLInputElement> document.querySelector('#pass');
    if (pass && this.user) this.user.password = pass.value;
    var jmbg = <HTMLInputElement> document.querySelector('#jmbg');
    if (jmbg && this.user) this.user.jmbg = jmbg.value;
    var occupation = <HTMLInputElement> document.querySelector('#occupation');
    if (occupation && this.user) this.user.occupation = occupation.value;
    var gender = <HTMLSelectElement> document.querySelector('#selGen');
    if (gender && this.user) this.user.gender = gender.value;
    const dto = new UserDTO(this.user);
    console.log(dto);
    this.userService.editUser(this.id, dto).subscribe((res: any) => {
      if(res) {
        console.log(true);
        var alert = document.querySelector('.alert');
        alert?.classList.replace('hidden', 'visible');
        this.resetValue();
      } else {
        console.log(false);
      }
    })
  }
}
