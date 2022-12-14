package group8.bloodbank.model; /***********************************************************************
 * Module:  Appointment.java
 * Author:  david
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Transient
    public Donor donor;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;

    @Column
    private LocalDateTime start;

    @Column
    private double duration;

}