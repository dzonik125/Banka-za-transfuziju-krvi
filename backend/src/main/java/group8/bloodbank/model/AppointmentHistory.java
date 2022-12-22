package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;
    @ManyToOne
    @JoinColumn(name = "medical_worker_id")
    private MedicalWorker medicalWorker;
    @Column
    private LocalDateTime date;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Anamnesis anamnesis;
    @Column
    private BloodType bloodType;
    @Column
    private double amount;
    @Column
    private AppointmentStatus status;

}
