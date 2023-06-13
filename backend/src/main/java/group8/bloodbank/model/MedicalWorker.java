package group8.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
public class MedicalWorker extends User {


    public MedicalWorker(Long id, String name, String surname, String password, Address adress, String jmbg, String email, String occupation, Gender gender) {
        super(id, name, surname, password, adress, jmbg, email, occupation, gender, UserType.MEDICAL_WORKER);
    }

    public MedicalWorker(String name, String surname, String email, String password, String jmbg, Address address, String occupation,  Gender gender, BloodBank bb) {
        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.MEDICAL_WORKER);
        this.bloodBank = bb;
    }

    public MedicalWorker(String name, String surname, String email, String password, String jmbg, Address address, String occupation,  Gender gender) {
        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.MEDICAL_WORKER);

    }

    public MedicalWorker() {

    }

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "medical_worker_appointments",
            joinColumns = @JoinColumn(name = "medical_worker_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private Set<Appointment> appointments;

    public MedicalWorker(Long id, String name, String surname, String email, String password, String jmbg, Address address, String occupation, Gender gender) {
        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.MEDICAL_WORKER);
        setId(id);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "medicalWorker", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AppointmentHistory> appointmentHistories;

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank newBloodBank) {
        this.bloodBank = newBloodBank;
    }
}