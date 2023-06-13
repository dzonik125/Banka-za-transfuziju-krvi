export class Anamnesis {
    id!: number;
    weight!: number;
    sicknessSymptoms!: boolean;
    skinIllness!: boolean;
    bloodPressureAbnormalities!: boolean;
    therapyIntake!: boolean;
    menstrualCycle!: boolean;
    dentalInterventions!: boolean;
    skinPiercings!: boolean;

  public constructor(obj?: any) {
      if (obj) {
          this.id = obj.id;
          this.bloodPressureAbnormalities = obj.bloodPressureAbnormalities;
          this.therapyIntake = obj.therapyIntake;
          this.weight = obj.weight;
          this.skinIllness = obj.skinIllness;
          this.menstrualCycle = obj.menstrualCycle;
          this.dentalInterventions = obj.dentalInterventions;
          this.skinPiercings = obj.skinPiercings;
          this.sicknessSymptoms = obj.sicknessSymptoms;
      }
  }
}
