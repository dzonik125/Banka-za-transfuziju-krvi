package group8.bloodbank.model; /***********************************************************************
 * Module:  BankComplaint.java
 * Author:  david
 * Purpose: Defines the Class BankComplaint
 ***********************************************************************/

public class BankComplaint extends Complaint {
    private BloodBank bloodBank;
    private Donor donor;

    public BankComplaint(String description, int id) {
        super(description, id);
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
}