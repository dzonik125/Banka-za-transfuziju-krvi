import { AppointmentStatus } from "./AppointmentStatus";
import { BloodType } from "./bloodType";
import { Item } from "./item";

export class AppointmentHistory{
    id!: number;
    bloodBankId!: number;
    date!: Date;
    donorId!: number;
    medicalWorkerId!: number;
    bloodType!: string;
    amount!: number;
    status!: AppointmentStatus
    item!: Item[]
    appointmentId!: number;

    constructor(obj?: any){
        if(obj) {
            this.id = obj.id;
            this.bloodBankId = obj.bloodBankId;
            this.date = obj.date;
            this.donorId = obj.donorId;
            this.medicalWorkerId = obj.medicalWorkerId;
            this.bloodType = obj.bloodType;
            this.amount = obj.amount;
            this.status = obj.status;
            this.item = obj.item;
            this.appointmentId = obj.appointmentId;
        }
    }
}