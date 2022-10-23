package group8.bloodbank.model; /***********************************************************************
 * Module:  Donor.java
 * Author:  david
 * Purpose: Defines the Class Donor
 ***********************************************************************/

import java.util.ArrayList;

/**
 * @pdOid c391d508-baeb-41be-a534-23d1021a74c9
 */
public class Donor extends User {

    public ArrayList<Complaint> complaint;
    public ArrayList<Survey> survey;
    private int points;
    private int penals;
    private Category category;
    private BloodType bloodType;

    public Donor(int id, String name, String surname, String username, String password, Address address, String jmbg, String email, String occupation, int penalty, Gender gender) {
        super(id, name, surname, username, password, address, jmbg, email, occupation, penalty, gender);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPenals() {
        return penals;
    }

    public void setPenals(int penals) {
        this.penals = penals;
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

}