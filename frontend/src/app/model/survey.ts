import { User } from "./user";

export class Survey {
    answer1!: boolean;
    answer2!: boolean;
    answer3!: boolean;
    answer4!: boolean;
    answer5!: boolean;
    answer6!: boolean;
    answer7!: boolean;
    answer8!: boolean;
    answer9!: boolean;
    answer10!: boolean;
   /// donor!: User;

  public constructor(obj?: any) {
      if (obj) {
          this.answer1 = obj.answer1;
          this.answer2 = obj.answer2;
          this.answer3 = obj.answer3;
          this.answer4 = obj.answer4;
          this.answer5 = obj.answer5;
          this.answer6 = obj.answer6;
          this.answer7 = obj.answer7;
          this.answer8 = obj.answer8;
          this.answer9 = obj.answer9;
          this.answer10 = obj.answer10;
         // this.donor = obj.donor;
      }
  }


}
