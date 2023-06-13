import { User } from './user';
import { BloodBank } from './bloodBank';
import { Address } from './address';
import { BloodType } from './bloodType';


export class Donor {
    name!: string
    surname!: string
    password!: string
    jmbg!: string
    email!: string
    occupation!: string
    gender!: string
    penalty: number = 0;
    points: number = 0;
    address!: Address;
    category: string = 'REGULAR';
    userType: string = 'DONOR';
    hasSurvey!: boolean;
    bloodType!: BloodType;




  public constructor(obj?: any) {
      if (obj) {
          this.name = obj.name;
          this.surname = obj.surname;
          this.password = obj.password;
          this.jmbg = obj.jmbg;
          this.email = obj.email;
          this.occupation = obj.occupation;
          this.gender = obj.gender;
          this.penalty = obj.penalty;
          this.address = obj.address;
          this.points = obj.points;
          this.category = obj.category;
          this.userType = obj.userType;
          this.hasSurvey = obj.hasSurvey;
          this.bloodType = obj.bloodType;
      }
  }

  public fullName(): String {
    return this.name + ' ' + this.surname;
  }
}
