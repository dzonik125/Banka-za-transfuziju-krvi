import { BloodBank } from "./bloodBank";

export class WorkingHours{
    bloodBank: BloodBank;
    startTime: Date;
    endTime: Date;

    constructor(bloodBank: BloodBank, startTime: Date, endTime: Date){
        this.bloodBank = bloodBank;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}