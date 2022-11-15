import { Address } from './address';
export class BloodBank {
    id: number = 0; 
    name: string = '';
    description: string= '';
    address!: Address;
    avgGrade: number | undefined;
    image!: string;
    medicalWorkers: any[] | undefined;
  

  public constructor(obj?: any) {
      if (obj) {
          this.id = obj.id
          this.name = obj.name;
          this.description = obj.description;
          this.address = obj.Address;
          this.avgGrade = obj.avgGrade;
          this.image = obj.image;
      }
  }
}
