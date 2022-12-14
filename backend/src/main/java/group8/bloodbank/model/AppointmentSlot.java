package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointmentSlot")
public class AppointmentSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    public AppointmentSlot(Long id, BloodBank bloodBank, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.start = start;
        this.end = this.start.plusMinutes(30);
    }
}
