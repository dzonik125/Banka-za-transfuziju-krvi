package group8.bloodbank.model;

public class BankComplaint extends Complaint {
    private BloodBank bloodBank;
    private Donor donor;

    public BankComplaint(String description, Long id) {
        super(id,description);
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