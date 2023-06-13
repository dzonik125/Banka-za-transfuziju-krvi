package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
    @Column
    private boolean lessThan50kg;
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