export class Appointment {
    title: string = '';
    start: string = '';
    id!: number;

  public constructor(obj?: any) {
      if (obj) {
          this.title = obj.title;
          this.start = obj.start;
          this.id = obj.id;
      }
  }
}
