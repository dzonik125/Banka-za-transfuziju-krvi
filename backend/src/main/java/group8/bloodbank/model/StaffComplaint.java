package group8.bloodbank.model;
public class StaffComplaint extends Complaint {

    public MedicalWorker medicalWorker;

    public StaffComplaint(String description, Long id) {
        super(id,description);
    }

    public MedicalWorker getMedicalWorker() {
        return medicalWorker;
    }

    public void setMedicalWorker(MedicalWorker medicalWorker) {
        this.medicalWorker = medicalWorker;
    }
}