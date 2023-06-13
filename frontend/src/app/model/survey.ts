import { User } from "./user";

export class Survey {
    lessThan50kg!: boolean;
    sicknessSymptoms!: boolean;
    skinIllness!: boolean;
    bloodPressureAbnormalities!: boolean;
    therapyIntake!: boolean;
    menstrualCycle!: boolean;
    dentalInterventions!: boolean;
    skinPiercings!: boolean;
    donor!: string;

  public constructor(obj?: any) {
      if (obj) {
          this.lessThan50kg = obj.lessThan50kg;
          this.sicknessSymptoms = obj.sicknessSymptoms;
          this.skinIllness = obj.skinIllness;
          this.bloodPressureAbnormalities = obj.anbloodPressureAbnormalitiesswer4;
          this.therapyIntake = obj.therapyIntake;
          this.menstrualCycle = obj.menstrualCycle;
          this.dentalInterventions = obj.dentalInterventions;
          this.skinPiercings = obj.skinPiercings;
          this.donor = obj.donor;
      }
  }


}
