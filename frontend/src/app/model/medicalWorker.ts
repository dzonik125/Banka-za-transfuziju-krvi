import { BloodBank } from './bloodBank';
import { Address } from './address';
export class MedicalWorker {
    name: string = '';
    surname: string = '';
    email: string = '';
    gender: string = '';
    

  public constructor(obj?: any) {
      if (obj) {
          this.name = obj.name;
          this.surname = obj.surname;
          this.email = obj.email;
          this.gender = obj.gender;
      }
  }

  public fullName(): String {
    return this.name + ' ' + this.surname;
  }
}
