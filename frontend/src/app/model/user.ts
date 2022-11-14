import { BloodBank } from './bloodBank';
import { Address } from './address';
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
          const a = new Address(obj.address);
          this.address = a;
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
