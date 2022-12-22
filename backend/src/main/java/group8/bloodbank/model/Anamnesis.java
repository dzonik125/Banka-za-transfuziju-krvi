package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anamnesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double weight;
    @Column
    private boolean sicknessSymptoms;
    @Column
    private boolean skinIllness;
    @Column
    private boolean bloodPressureAbnormalities;
    @Column
    private boolean therapyIntake;
    @Column
    private boolean menstrualCycle;
    @Column
    private boolean dentalInterventions;
    @Column
    private boolean skinPiercings;
}
