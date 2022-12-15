import { BloodBank } from "./bloodBank";
import { BloodType } from "./bloodType";
import { SubscriptionStatus } from "./subcriptionStatus";

export class MonthlySubscription {
    bloodBank!: BloodBank;
    bloodUnits!: Map<BloodType, number>
    deliveryDate!: Date;
    status!: SubscriptionStatus

    public constructor(bloodBank:BloodBank, bloodUnits:Map<BloodType, number>, deliveryDate:Date, status:SubscriptionStatus){
        this.bloodBank = bloodBank;
        this.bloodUnits = bloodUnits;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }
}