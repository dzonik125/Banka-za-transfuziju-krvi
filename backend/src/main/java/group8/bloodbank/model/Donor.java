package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public ArrayList<Complaint> getComplaint() {
        return complaint;
    }

    public void setComplaint(ArrayList<Complaint> complaint) {
        this.complaint = complaint;
    }

    public ArrayList<Survey> getSurvey() {
        return survey;
    }

    public void setSurvey(ArrayList<Survey> survey) {
        this.survey = survey;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

}