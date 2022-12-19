export class Appointment {
    title: string = '';
    start: string = '';
  

  public constructor(obj?: any) {
      if (obj) {
          this.title = obj.title;
          this.start = obj.start;
      }
  }
}
