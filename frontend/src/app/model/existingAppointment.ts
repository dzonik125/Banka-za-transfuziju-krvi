import { BloodBank } from 'src/app/model/bloodBank';
export class ExistingAppointment {

  public id: any;
  public bloodBank!: BloodBank;
  public startTime!: Date;
  public endTime!: Date;
  public status!: string;

  public constructor(obj?: any) {
      if (obj) {
        this.id = obj.id;
        this.bloodBank = obj.bloodBank;
        this.startTime = obj.startTime;
        this.endTime = obj.endTime;
        this.status = obj.status;
      }
  }

}
