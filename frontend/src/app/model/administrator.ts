import { BloodBank } from './bloodBank';
import { Address } from './address';
export class Administrator {
    name: string = '';
    surname: string = '';
    password: string = '';
    jmbg: string = '';
    email: string = '';
    occupation: string ='';
    gender: string = '';
    address!: Address;
    bloodBank!: BloodBank;
    id: number = 0;
    firstLogin!: boolean;

  public constructor(obj?: any) {
      if (obj) {
          this.id = obj.id;
          this.name = obj.name;
          this.surname = obj.surname;
          this.password = obj.password;
          this.jmbg = obj.jmbg;
          this.email = obj.email;
          this.occupation = obj.occupation;
          this.gender = obj.gender;
          this.address = obj.address;
          this.firstLogin = obj.firstLogin;
      }
  }

  public fullName(): String {
    return this.name + ' ' + this.surname;
  }
}
