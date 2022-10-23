package group8.bloodbank.model; /***********************************************************************
 * Module:  BloodBank.java
 * Author:  david
 * Purpose: Defines the Class BloodBank
 ***********************************************************************/

import java.util.ArrayList;
import java.util.HashMap;

public class BloodBank {

    public ArrayList<MedicalWorker> medicalWorker;
    public ArrayList<Item> item;
    public ArrayList<Appointment> appointment;
    public Address address;
    public WorkingHours workingHours;
    private int id;
    private String name;
    private String description;
    private double avgGrade;
    private HashMap<BloodType, Double> bloodType;

    public BloodBank(int id, String name, String description, double avgGrade, HashMap<BloodType, Double> bloodType,
                     ArrayList<MedicalWorker> medicalWorker, ArrayList<Item> item, ArrayList<Appointment> appointment, Address address, WorkingHours workingHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avgGrade = avgGrade;
        this.bloodType = bloodType;
        this.medicalWorker = medicalWorker;
        this.item = item;
        this.appointment = appointment;
        this.address = address;
        this.workingHours = workingHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public HashMap<BloodType, Double> getBloodType() {
        return bloodType;
    }

    public void setBloodType(HashMap<BloodType, Double> bloodType) {
        this.bloodType = bloodType;
    }

    public ArrayList<MedicalWorker> getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(ArrayList<MedicalWorker> medicalWorker) {
        this.medicalWorker = medicalWorker;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    public ArrayList<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(ArrayList<Appointment> appointment) {
        this.appointment = appointment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }
}