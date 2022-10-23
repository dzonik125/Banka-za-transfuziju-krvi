package group8.bloodbank.model; /***********************************************************************
 * Module:  StaffComplaint.java
 * Author:  david
 * Purpose: Defines the Class StaffComplaint
 ***********************************************************************/

/**
 * @pdOid bc1e374c-40f5-43ba-9fbb-ad94acc255a1
 */
public class StaffComplaint extends Complaint {
    /**
     * @pdRoleInfo migr=no name=MedicalWorker assc=association23 mult=0..1
     */
    public MedicalWorker medicalWorker;

    public StaffComplaint(String description, int id) {
        super(description, id);
    }

    public MedicalWorker getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(MedicalWorker medicalWorker) {
        this.medicalWorker = medicalWorker;
    }
}