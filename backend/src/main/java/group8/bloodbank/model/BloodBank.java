package group8.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name="blood_bank")
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    public ArrayList<Item> item;

    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointment;
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

    @Column
    private String image;

    @ElementCollection
    @CollectionTable(name="bloodType_bloodBank", joinColumns=@JoinColumn(name="bloodBank_id"))
    @MapKeyColumn(name = "bloodType_key")
    @Column(name="bloodType_amount")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<BloodType, Double> bloodType;

    public BloodBank(Long id, String name, String description, double avgGrade, Map<BloodType, Double> bloodType,
                     ArrayList<MedicalWorker> medicalWorkers, ArrayList<Item> item, ArrayList<Appointment> appointment, Address address, WorkingHours workingHours, String apiKey, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avgGrade = avgGrade;
        this.bloodType = bloodType;
        this.appointment = appointment;
        this.address = address;
        this.workingHours = workingHours;
        this.apiKey = "";
        this.image = image;
    }

    public BloodBank(Long id, String name, String description, Address address, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
    }


    public BloodBank() {

    }

    public BloodBank(String name, String description, Address address, String image) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
    }
}