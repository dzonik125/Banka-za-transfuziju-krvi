package group8.bloodbank.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {

    private Long id;

    private String donor;

    private boolean lessThan50kg;

    private boolean sicknessSymptoms;

    private boolean skinIllness;

    private boolean bloodPressureAbnormalities;

    private boolean therapyIntake;

    private boolean menstrualCycle;

    private boolean dentalInterventions;

    private boolean skinPiercings;
}
