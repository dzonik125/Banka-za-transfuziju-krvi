package group8.bloodbank.model; /***********************************************************************
 * Module:  BloodBank.java
 * Author:  david
 * Purpose: Defines the Class BloodBank
 ***********************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ArrayList<MedicalWorker> medicalWorker;
    public ArrayList<Item> item;
    public ArrayList<Appointment> appointment;

    @Transient
    public WorkingHours workingHours;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double avgGrade;

    @Transient
    public Address address;


    @ElementCollection
    @CollectionTable(name="bloodType_bloodBank", joinColumns=@JoinColumn(name="bloodBank_id"))
    @MapKeyColumn(name = "bloodType_key")
    @Column(name="bloodType_amount")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<BloodType, Double> bloodType;

    public BloodBank(Long id, String name, String description, double avgGrade, Map<BloodType, Double> bloodType,
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Map<BloodType, Double> getBloodType() {
        return bloodType;
    }

    public void setBloodType(Map<BloodType, Double> bloodType) {
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