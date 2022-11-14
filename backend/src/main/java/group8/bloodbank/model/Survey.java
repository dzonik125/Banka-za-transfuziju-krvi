package group8.bloodbank.model; /***********************************************************************
 * Module:  Survey.java
 * Author:  david
 * Purpose: Defines the Class Survey
 ***********************************************************************/

import java.util.ArrayList;


public class Survey {

    private Donor donor;
    private ArrayList<String> answers;

    public Survey(Donor donor, ArrayList<String> answers) {
        this.donor = donor;
        this.answers = answers;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}