export class Appointment {
    title: string = '';
    start: string = '';
    id!: number;
    donorId!: number;
    status!: string;

  public constructor(obj?: any) {
      if (obj) {
          this.title = obj.title;
          this.start = obj.start;
          this.id = obj.id;
          this.donorId = obj.donorId;
          this.status = obj.status;
      }
  }
}
