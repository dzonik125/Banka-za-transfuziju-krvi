import { BloodBank } from "./bloodBank";
import { Donor } from "./donor";
import { MedicalWorker } from "./medicalWorker";

export class AppointmentDTO {
    medicalWorkerIds: number[];
    donor_id: number;
    bloodBank: BloodBank;
    start: Date;
    duration: number;

    constructor(medicalWorker: number[], donor_id: number, bloodBank: BloodBank, start: Date, duration: number){
        this.medicalWorkerIds = medicalWorker;
        this.donor_id = donor_id;
        this.bloodBank = bloodBank;
        this.start = start;
        this.duration =  duration;
    }
}