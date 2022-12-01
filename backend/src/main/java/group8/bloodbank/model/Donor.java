package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Donor extends User {
    @Transient
    public ArrayList<Complaint> complaint;
    @Transient
    public ArrayList<Survey> survey;
    @Column
    private int points;
    @Column
    private int penalty;

    @Column
    private Category category;
    @Column
    private BloodType bloodType;


    public Donor(Long id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, Gender gender, int points, int penalty, Category category, BloodType bloodType) {
        super(id, name, surname, password, address, jmbg, email, occupation, gender, UserType.DONOR);
        this.penalty = penalty;
        this.points = points;
        this.category = category;
        this.bloodType = bloodType;
    }

    public Donor(String name, String surname, String password, Address address, String jmbg, String email, String occupation, Gender gender, int points, int penalty, Category category, BloodType bloodType) {
        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.DONOR);
        this.penalty = penalty;
        this.points = points;
        this.category = category;
        this.bloodType = bloodType;
    }

    public Donor() {

    }

}