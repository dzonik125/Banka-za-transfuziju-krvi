package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.MedicalWorker;

import java.util.List;

public interface MedicalWorkerService {

    public MedicalWorker saveMedicalWorker(MedicalWorker medicalWorker);

    public List<MedicalWorker> getAll();

    List<MedicalWorker> getAllByBloodBankIsNull();
}
