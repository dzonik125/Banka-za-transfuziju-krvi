package group8.bloodbank.mapper;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.DTO.AppointmentPreviewDTO;
import group8.bloodbank.model.DTO.MedicalExaminationDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.MedicalExamination;
import group8.bloodbank.service.interfaces.MedicalExaminationService;

public class MedicalExaminationMapper {

    public static MedicalExamination DTOtoModel(MedicalExaminationDTO medicalExaminationDTO, Donor donor, BloodBank bloodBank) {
        if ( medicalExaminationDTO == null ) {
            return null;
        }
        MedicalExamination medicalExamination = new MedicalExamination();
        medicalExamination.setAmount(medicalExaminationDTO.getAmount());
        medicalExamination.setBloodType(medicalExaminationDTO.getBloodType());
        medicalExamination.setBloodBank(bloodBank);
        medicalExamination.setDonor(donor);
        return medicalExamination;
    }
}
