import { RegisteredUser } from "./registered-user.model";

export class RegisterRequest implements NewRegisterRequest {

    registerUser: RegisteredUser;
    email: string;
    username: string;
    password: string;

    constructor(nrui: NewRegisterRequest)
    {
        this.registerUser = nrui.registerUser;
        this.email = nrui.email;
        this.username = nrui.username;
        this.password = nrui.password;
    }
}
interface NewRegisterRequest{

    registerUser: RegisteredUser;
    email: string;
    username: string;
    password: string;

}