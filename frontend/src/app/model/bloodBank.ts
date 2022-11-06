import { Address } from './address';
export class BloodBank {
    name: string = '';
    description: string= '';
    address!: Address;

  public constructor(obj?: any) {
      if (obj) {
          this.name = obj.name;
          this.description = obj.description;
          this.address = obj.Address;
      }
  }
}
