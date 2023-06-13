import { BloodBank } from "./bloodBank";


export class Item {
    id!: number;
    bloodBank!: BloodBank;
    quantity!: number;
    name!: string;

    constructor(obj?: any){
        if(obj) {
            this.id = obj.id;
            this.bloodBank = obj.bloodBank;
            this.quantity = obj.quantity;
            this.name =  obj.name;
        }
    }
}