package group8.bloodbank.service.implementations;

import group8.bloodbank.model.MedicalExamination;
import group8.bloodbank.repository.MedicalExaminationRepository;
import group8.bloodbank.service.interfaces.MedicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalExaminationServiceImpl implements MedicalExaminationService {


    private MedicalExaminationRepository repository;
    private BloodBankServiceImpl bloodBankService;

    @Autowired
    public MedicalExaminationServiceImpl(MedicalExaminationRepository repository, BloodBankServiceImpl bloodBankService) {
        this.repository = repository;
        this.bloodBankService = bloodBankService;
    }
    @Override
    public MedicalExamination create(MedicalExamination medicalExamination) {
        bloodBankService.updateBloodAmount(medicalExamination);
        return repository.save(medicalExamination);
    }

    @Override
    public Long getBloodBankId(Long medicalWorkerId) {
        return repository.findById(medicalWorkerId).get().getBloodBank().getId();
    }
}
