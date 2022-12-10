import { Address } from "src/app/model/address";
import { Gender } from "../../util/enum/gender";


export class RegisteredUser implements NewRegisteredUserInterface{
    name!: string
    surname!: string
    password!: string
    jmbg!: string
    email!: string
    occupation!: string
    gender: Gender
    penalty: number = 0;
    points: number = 0;
    address!: Address;
    category: string = 'REGULAR';
    userType: string = 'DONOR';

    constructor(nrui: NewRegisteredUserInterface)
    {
        this.firstName = nrui.firstName;
        this.lastName = nrui.lastName;
        this.email = nrui.email;
        this.pin = nrui.pin;

        this.gender = nrui.gender;
        this.address = nrui.address;

    }
  firstName: string;
  lastName: string;
  pin: string;
}

interface NewRegisteredUserInterface{
    firstName: string;
    lastName: string;
    email: string;
    pin: string;
    gender: Gender;
    address: Address;
}
