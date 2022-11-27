package group8.bloodbank.service.implementations;

import group8.bloodbank.model.*;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.MedicalWorkerRepository;
import group8.bloodbank.service.interfaces.MedicalWorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalWorkerServiceImpl implements MedicalWorkerService {

    MedicalWorkerRepository medicalWorkerRepository;
    BloodBankRepository bloodBankRepository;

    @Autowired
    public MedicalWorkerServiceImpl(MedicalWorkerRepository repo, BloodBankRepository bloodBankRepository) {
        this.medicalWorkerRepository = repo;
        this.bloodBankRepository = bloodBankRepository;

//        MedicalWorker medicalWorker1 = new MedicalWorker("Petar", "Kojic", "petar@mail.com", "petar123", "1231231231234", new Address("Srbija", "Novi Sad", "Strazilovska", "12B"), "", Gender.MALE, null);
//        MedicalWorker medicalWorker2 = new MedicalWorker("Ljuban", "Savic", "ljubi1@mail.com", "ljuba123", "1131231231234", new Address("Srbija", "Beograd", "Dusanova", "52"), "", Gender.MALE);
//        MedicalWorker medicalWorker3 = new MedicalWorker("Milan", "Anic", "milance@mail.com", "milo123", "1131271231234", new Address("Srbija", "Beograd", "Danilova","33"), "", Gender.MALE);
//
//       medicalWorkerRepository.save(medicalWorker1);
//        medicalWorkerRepository.save(medicalWorker2);
//        medicalWorkerRepository.save(medicalWorker3);

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
