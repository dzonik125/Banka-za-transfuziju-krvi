package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.MedicalExamination;

public interface MedicalExaminationService {

    MedicalExamination create(MedicalExamination medicalExamination);

    Long getBloodBankId(Long medicalWorkerId);
}
