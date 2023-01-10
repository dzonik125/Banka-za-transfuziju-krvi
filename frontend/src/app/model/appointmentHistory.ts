import { Anamnesis } from "./anamnesis";
import { AppointmentStatus } from "./AppointmentStatus";
import { BloodBank } from "./bloodBank";
import { BloodType } from "./bloodType";
import { Donor } from "./donor";
import { MedicalWorker } from "./medicalWorker";

export class AppointmentHistory{
    id!: number;
    bloodBank!: BloodBank;
    date!: Date;
    donor!: Donor;
    medicalWorker!: MedicalWorker;
    anamnesis!: Anamnesis;
    bloodType!: BloodType;
    amount!: number;
    status!: AppointmentStatus

    constructor(obj?: any){
        if(obj) {
            this.id = obj.id;
            this.bloodBank = obj.bloodBank;
            this.date = obj.date;
            this.donor = obj.donor;
            this.medicalWorker = obj.medicalWorker;
            this.anamnesis = obj.anamnesis;
            this.bloodType = obj.bloodType;
            this.amount = obj.amount;
            this.status = obj.status;
        }
    }
}