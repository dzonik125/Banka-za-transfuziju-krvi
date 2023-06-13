import { Address } from "./address";

export class VisitHistory{
    public id!: number;
    public name!: string;
    public surname!: string;
    public type!: string;
    public donationTime!: Date;

    public constructor(obj?: any){
      if(obj){
        this.id = obj.id;
        this.name = obj.name;
        this.surname = obj.surname;
        this.type = obj.type;
        this.donationTime = obj.donationTime
      }
    }
}
