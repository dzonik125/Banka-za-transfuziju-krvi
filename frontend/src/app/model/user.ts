import { BloodBank } from './bloodBank';
import { Address } from './address';
<<<<<<< HEAD
export class User{
    private _name: string = '';
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
    private _surname: string = '';
  public get surname(): string {
    return this._surname;
  }
  public set surname(value: string) {
    this._surname = value;
  }
    private _password: string = '';
  public get password(): string {
    return this._password;
  }
  public set password(value: string) {
    this._password = value;
  }
    private _jmbg: string = '';
  public get jmbg(): string {
    return this._jmbg;
  }
  public set jmbg(value: string) {
    this._jmbg = value;
  }
    private _email: string = '';
  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }
    private _occupation: string = '';
  public get occupation(): string {
    return this._occupation;
  }
  public set occupation(value: string) {
    this._occupation = value;
  }
    private _gender: string = '';
  public get gender(): string {
    return this._gender;
  }
  public set gender(value: string) {
    this._gender = value;
  }
    private _penalty: number = 0;
  public get penalty(): number {
    return this._penalty;
  }
  public set penalty(value: number) {
    this._penalty = value;
  }
    private _address!: Address;
  public get address(): Address {
    return this._address;
  }
  public set address(value: Address) {
    this._address = value;
  }
=======
export class User {
    name: string = '';
    surname: string = '';
    password: string = '';
    jmbg: string = '';
    email: string = '';
    occupation: string ='';
    gender: string = '';
    penalty: number = 0;
    points: number = 0;
    address!: Address;
    category: string = 'REGULAR';
>>>>>>> 85f0bffdff605c33fbec22ef3883b6bb1f5ebdba

  private _id: number | undefined;
  public set id(value: number) {
    this._id = value;
  }

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
<<<<<<< HEAD
          const a = new Address(obj.address);
          this.address = a;
=======
          this.address = obj.address;
          this.points = obj.points;
          this.category = obj.category;
>>>>>>> 85f0bffdff605c33fbec22ef3883b6bb1f5ebdba
      }
  }

  public EditUser(obj?: any) {
    if(obj) {
      this.name = obj.name;
      this.surname = obj.surname;
      this.password = obj.password;
      this.jmbg = obj.jmbg;
      this.email = obj.email;
      this.occupation = obj.occupation;
      this.gender = obj.gender;
      this.penalty = obj.penalty;
      this.address = obj.address;
    }
  }

  public fullName(): String {
    return this.name + ' ' + this.surname;
  }
}
