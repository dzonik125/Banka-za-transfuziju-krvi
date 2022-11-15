package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.MedicalWorkerRepository;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalWorkerServiceImpl implements MedicalWorkerService {

    MedicalWorkerRepository medicalWorkerRepository;
    BloodBankRepository bloodBankRepository;

    @Autowired
    public MedicalWorkerServiceImpl(MedicalWorkerRepository repo, BloodBankRepository bloodBankRepository) {
        this.medicalWorkerRepository = repo;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public MedicalWorker saveMedicalWorker(MedicalWorker medicalWorker) {
        return medicalWorkerRepository.save(medicalWorker);
    }

    @Override
    public List<MedicalWorker> getAll() {
        return medicalWorkerRepository.findAll();
    }

    @Override
    public List<MedicalWorker> getAllByBloodBankIsNull() {
        return medicalWorkerRepository.getAllByBloodBankIsNull();
    }

    @Override
    public List<MedicalWorker> getAllByBloodBank(Long bloodBankId) {
        return medicalWorkerRepository.getAllByBloodBank(bloodBankId);
    }

    @Override
    public void SetBloodBankIDsForSelectedMedicalWorkers(List<MedicalWorker> medicalWorkers, BloodBank bloodBank) {
        for (MedicalWorker mw: medicalWorkers) {
        mw.setBloodBank(bloodBank);
        medicalWorkerRepository.updateMedicalWorker(mw.getId(), bloodBank);
        }

    }

    @Override
    public void updateMedicalWorkerBloodBank(MedicalWorker medicalWorker, BloodBank bloodBank) {
        medicalWorkerRepository.updateMedicalWorker(medicalWorker.getId(), bloodBank);
    }

}
