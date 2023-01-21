package group8.bloodbank.controller;

import group8.bloodbank.mapper.MedicalExaminationMapper;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.DTO.MedicalExaminationDTO;
import group8.bloodbank.model.MedicalExamination;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.MedicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicalExamination")
public class MedicalExaminationController {

    private MedicalExaminationService medicalExaminationService;
    private DonorService donorService;
    private BloodBankService bloodBankService;

    @Autowired
    public MedicalExaminationController(MedicalExaminationService meService, DonorService donorService, BloodBankService bloodBankService) {
        this.medicalExaminationService = meService;
        this.donorService = donorService;
        this.bloodBankService = bloodBankService;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/createME")
    public ResponseEntity<MedicalExamination> createMedicalExamination(@RequestBody MedicalExaminationDTO medicalExaminationDTO) {
        MedicalExamination medicalExamination = MedicalExaminationMapper.DTOtoModel(medicalExaminationDTO, donorService.getByEmail(medicalExaminationDTO.getEmail()), bloodBankService.getByName(medicalExaminationDTO.getBb()));

        try {
            medicalExaminationService.create(medicalExamination);
            return new ResponseEntity<MedicalExamination>(medicalExamination, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<MedicalExamination>(medicalExamination, HttpStatus.CONFLICT);
        }
    }

}
