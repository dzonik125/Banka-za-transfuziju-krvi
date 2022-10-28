package group8.bloodbank.model; /***********************************************************************
 * Module:  Appointment.java
 * Author:  david
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import java.util.ArrayList;
import java.time.LocalDateTime;
public class Appointment {
    public ArrayList<MedicalWorker> medicalWorker;
    public Donor donor;
    public BloodBank bloodBank;
    private LocalDateTime start;
    private double duration;
    private int id;

    public Appointment(LocalDateTime start, double duration, int id, ArrayList<MedicalWorker> medicalWorker, Donor donor, BloodBank bloodBank) {
        this.start = start;
        this.duration = duration;
        this.id = id;
        this.medicalWorker = medicalWorker;
        this.donor = donor;
        this.bloodBank = bloodBank;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MedicalWorker> getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(ArrayList<MedicalWorker> medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}