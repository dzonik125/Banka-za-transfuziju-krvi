package group8.bloodbank.model; /***********************************************************************
 * Module:  WorkingHours.java
 * Author:  david
 * Purpose: Defines the Class WorkingHours
 ***********************************************************************/

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @pdOid aec80d47-2cf1-4648-9f00-ff070b4eddff
 */
@Entity
@Table(name = "workingHours")
public class WorkingHours {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;

    public WorkingHours(LocalDateTime startTime, LocalDateTime endTime, BloodBank bloodBank) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bloodBank = bloodBank;
    }

    public WorkingHours() {

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}