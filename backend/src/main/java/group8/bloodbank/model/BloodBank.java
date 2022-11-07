package group8.bloodbank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Map;

@Entity
@Getter
@Setter
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Address address;

    @Column
    private String apiKey;

    @ElementCollection
    @CollectionTable(name="bloodType_bloodBank", joinColumns=@JoinColumn(name="bloodBank_id"))
    @MapKeyColumn(name = "bloodType_key")
    @Column(name="bloodType_amount")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<BloodType, Double> bloodType;

    public BloodBank(Long id, String name, String description, double avgGrade, Map<BloodType, Double> bloodType,
                     ArrayList<MedicalWorker> medicalWorker, ArrayList<Item> item, ArrayList<Appointment> appointment, Address address, WorkingHours workingHours, String apiKey) {
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
        this.apiKey = "";
    }

    public BloodBank(String name, String description, Address address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }


    public BloodBank() {

    }
}