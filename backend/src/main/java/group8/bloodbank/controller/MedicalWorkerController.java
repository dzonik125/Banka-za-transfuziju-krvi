package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.MedicalWorkerDTO;
import group8.bloodbank.model.Gender;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicalWorker")
public class MedicalWorkerController {
    private MedicalWorkerService medicalWorkerService;

    @Autowired
    public MedicalWorkerController(MedicalWorkerService medicalWorkerService) {
        this.medicalWorkerService = medicalWorkerService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalWorker> saveMedicalWorker(@RequestBody MedicalWorkerDTO medicalWorkerDTO)  {
        MedicalWorker medicalWorker = new MedicalWorker(medicalWorkerDTO.name, medicalWorkerDTO.surname, medicalWorkerDTO.email, medicalWorkerDTO.password, medicalWorkerDTO.jmbg , medicalWorkerDTO.address, medicalWorkerDTO.occupation, Gender.valueOf(medicalWorkerDTO.gender.toUpperCase()));
        try {
            medicalWorker = medicalWorkerService.saveMedicalWorker(medicalWorker);
            return new ResponseEntity<MedicalWorker>(medicalWorker, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<MedicalWorker>(medicalWorker, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalWorker> getAll() {
        return medicalWorkerService.getAll();
    }

}
