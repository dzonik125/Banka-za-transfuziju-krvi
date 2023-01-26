export class Address {
  public country: string = '';

  public city: string = '';
  
  public street: string = '';

  public number: string = '';


  public constructor(obj?: any) {
      if (obj) {
          this.country = obj.country;
          this.city = obj.city;
          this.street = obj.street;
          this.number = obj.number;
      }
  }

    public EditAdress(obj?: any): void{
      if (obj) {
        this.country = obj.country;
        this.city = obj.city;
        this.street = obj.street;
        this.number = obj.number;
    }
    }
}
