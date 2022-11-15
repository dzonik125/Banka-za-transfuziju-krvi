package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.MedicalWorker;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalWorkerService {

    public MedicalWorker saveMedicalWorker(MedicalWorker medicalWorker);

    public List<MedicalWorker> getAll();

    List<MedicalWorker> getAllByBloodBankIsNull();

    public List<MedicalWorker> getAllByBloodBank(Long bloodBankId);

    void SetBloodBankIDsForSelectedMedicalWorkers(List<MedicalWorker> medicalWorkers, BloodBank bloodBank);

    void updateMedicalWorkerBloodBank(MedicalWorker medicalWorker, BloodBank bloodBank);
}
