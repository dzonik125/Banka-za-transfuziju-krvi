package group8.bloodbank.model; /***********************************************************************
 * Module:  WorkingHours.java
 * Author:  david
 * Purpose: Defines the Class WorkingHours
 ***********************************************************************/

import javax.persistence.*;
import java.sql.Time;
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
    private BloodBank bloodBank;
    @Column
    private Time startTime;
    @Column
    private Time endTime;

    public WorkingHours(Time startTime, Time endTime, BloodBank bloodBank) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bloodBank = bloodBank;
    }

    public WorkingHours() {

    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}