import { Address } from "./address";

export class UserDTO{
    public name!: string;
    public surname!: string;
    public password!: string;
    public address!: Address;
    public jmbg!: string;
    public email!: string;
    public occupation!: string;
    public gender!: string;

    public constructor(obj?: any){
      if(obj){
        this.name = obj.name;
        this.surname = obj.surname;
        this.password = obj.password;
        this.address = obj.address;
        this.jmbg = obj.jmbg;
        this.occupation = obj.occupation;
        this.gender = obj.gender;
        this.email = obj.email;
      }
    }
}
