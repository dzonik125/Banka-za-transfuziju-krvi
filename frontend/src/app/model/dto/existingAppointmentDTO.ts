import { BloodBank } from 'src/app/model/bloodBank';
export class ExistingAppointmentDTO {

  public id: any = '';
  public bloodBank: string = '';
  public startTime!: Date;
  public endTime!: Date;
  public status: string = '';
  public donor: string = '';

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
