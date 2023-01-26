import { BloodBank } from 'src/app/model/bloodBank';
import { Donor } from './donor';
import { User } from './user';
export class ExistingAppointment {

  public id: any;
  public bloodBank!: BloodBank;
  public startTime!: Date;
  public endTime!: Date;
  public status!: string;
  public donor!: string;

  public constructor(obj?: any) {
      if (obj) {
        this.id = obj.id;
        this.bloodBank = obj.bloodBank;
        this.startTime = obj.startTime;
        this.endTime = obj.endTime;
        this.status = obj.status;
        this.donor = obj.donor;
      }
  }

}
