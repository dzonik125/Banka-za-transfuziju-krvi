package group8.bloodbank.model; /***********************************************************************
 * Module:  Appointment.java
 * Author:  david
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "appointments", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<MedicalWorker> medicalWorker;

    @Column
    public Long donor_id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;

    @Column
    private LocalDateTime start;

    @Column
    private double duration;

    @Transient
    public Donor donor;

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank newBloodBank) {
        this.bloodBank = newBloodBank;
    }
}