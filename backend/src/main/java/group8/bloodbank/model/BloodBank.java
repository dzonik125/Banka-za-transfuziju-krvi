package group8.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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

    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER)
    public List<Item> item;

    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointment;

    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AppointmentHistory> appointmentHistories;

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

    @ElementCollection(fetch = FetchType.EAGER)
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
        this.apiKey = apiKey;
        this.image = image;
    }

    public BloodBank(Long id, String name, String description, Address address, String image, double avgGrade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.avgGrade = avgGrade;
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