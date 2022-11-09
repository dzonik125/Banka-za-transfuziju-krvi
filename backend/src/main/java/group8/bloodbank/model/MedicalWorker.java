package group8.bloodbank.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
public class MedicalWorker extends User {

    @OneToOne
    @JoinColumn(name = "blood_bank_id")
    public BloodBank bloodBank;

    public MedicalWorker(Long id, String name, String surname, String password, Address adress, String jmbg, String email, String occupation, Gender gender) {
        super(id, name, surname, password, adress, jmbg, email, occupation, gender);


    }

    public MedicalWorker(String name, String surname, String email, Gender gender) {
        super(name, surname, email, gender);

    }

    public MedicalWorker() {

    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank newBloodBank) {
        this.bloodBank = newBloodBank;
    }
}