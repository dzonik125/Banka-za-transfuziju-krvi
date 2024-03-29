package group8.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_slot")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSlot {

    @Version
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    public Donor donor;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime startTime;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime endTime;

    @Column
    private AppointmentStatus status;

    public AppointmentSlot(Long id, BloodBank bloodBank, Donor donor, LocalDateTime startTime, LocalDateTime endTime, AppointmentStatus status) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.donor = donor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
