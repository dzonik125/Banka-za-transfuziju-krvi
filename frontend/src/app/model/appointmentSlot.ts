import { AppointmentStatus } from "./AppointmentStatus";
import { BloodBank } from "./bloodBank";

export class AppointmentSlot{
    bloodBank: BloodBank;
    startTime: Date;
    endTime: Date;
    status: AppointmentStatus;

    constructor(bloodBank: BloodBank, startTime: Date, endTime: Date, status: AppointmentStatus){
        this.bloodBank = bloodBank;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}