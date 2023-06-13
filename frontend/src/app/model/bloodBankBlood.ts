
export class BloodBankBlood {
    A_POSITIVE!: number; 
    A_NEGATIVE!: number;
    B_POSITIVE!: number;
    B_NEGATIVE!: number;
    AB_POSITIVE!: number;
    AB_NEGATIVE!: number;
    O_POSITIVE!: number;
    O_NEGATIVE!: number;
  

  public constructor(obj?: any) {
      if (obj) {
          this.A_POSITIVE = obj.A_POSITIVE
          this.A_NEGATIVE = obj.A_NEGATIVE;
          this.B_POSITIVE = obj.B_POSITIVE;
          this.B_NEGATIVE = obj.B_NEGATIVE;
          this.AB_POSITIVE = obj.AB_POSITIVE;
          this.AB_NEGATIVE = obj.AB_NEGATIVE;
          this.O_POSITIVE = obj.O_POSITIVE;
          this.O_NEGATIVE = obj.O_NEGATIVE;
      }
  }
}
