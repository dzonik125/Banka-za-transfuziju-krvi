import { BloodBank } from 'src/app/model/bloodBank';
export class ExistingAppointment {

  public id: any;
  public bloodBank!: BloodBank;
  public startTime: any;
  public endTime: any;

  public constructor(obj?: any) {
      if (obj) {
        this.id = obj.id;
        this.bloodBank = obj.bloodBank;
        this.startTime = obj.startTime;
        this.endTime = obj.endTime;
      }
  }

}
