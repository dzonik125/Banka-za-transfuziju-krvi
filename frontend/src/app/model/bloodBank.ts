import { Address } from './address';
export class BloodBank {
    name: string = '';
    description: string= '';
    address!: Address;
    avgGrade: number | undefined;
    image!: string;
    medicalWorkers: any[] | undefined;
  

  public constructor(obj?: any) {
      if (obj) {
          this.name = obj.name;
          this.description = obj.description;
          this.address = obj.Address;
          this.avgGrade = obj.avgGrade;
          this.image = obj.image;
      }
  }
}
