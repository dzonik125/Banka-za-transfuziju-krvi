package group8.bloodbank.controller;

import clojure.lang.IFn;
import group8.bloodbank.model.DTO.MedicalWorkerDTO;
import group8.bloodbank.model.Gender;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.model.WorkingHours;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import group8.bloodbank.service.interfaces.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicalWorker")
public class MedicalWorkerController {

    private MedicalWorkerService medicalWorkerService;
    private WorkingHoursService workingHoursService;

    @Autowired
    public MedicalWorkerController(MedicalWorkerService medicalWorkerService, WorkingHoursService workingHoursService){
        this.medicalWorkerService = medicalWorkerService;
        this.workingHoursService = workingHoursService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalWorker> saveMedicalWorker(@RequestBody MedicalWorkerDTO medicalWorkerDTO) {
        MedicalWorker medicalWorker = new MedicalWorker(medicalWorkerDTO.name, medicalWorkerDTO.surname, medicalWorkerDTO.email, medicalWorkerDTO.password, medicalWorkerDTO.jmbg, medicalWorkerDTO.address, medicalWorkerDTO.occupation, Gender.valueOf(medicalWorkerDTO.gender.toUpperCase()));
        try {
            medicalWorker = medicalWorkerService.saveMedicalWorker(medicalWorker);
            return new ResponseEntity<MedicalWorker>(medicalWorker, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<MedicalWorker>(medicalWorker, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateMedicalWorkerBloodBank(@RequestBody MedicalWorkerDTO medicalWorkerDTO) {
        MedicalWorker medicalWorker = new MedicalWorker(medicalWorkerDTO.id, medicalWorkerDTO.name, medicalWorkerDTO.surname, medicalWorkerDTO.email, medicalWorkerDTO.password, medicalWorkerDTO.jmbg, medicalWorkerDTO.address, medicalWorkerDTO.occupation, Gender.valueOf(medicalWorkerDTO.gender.toUpperCase()));
        medicalWorker.setBloodBank(medicalWorkerDTO.bloodBank);
        try {
            medicalWorkerService.updateMedicalWorkerBloodBank(medicalWorker, medicalWorker.getBloodBank());
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalWorker> getAll() {
        return medicalWorkerService.getAll();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    @GetMapping(value = "/freeMedicalWorkers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalWorkerDTO> getAllByBloodBankIsNull() {
        return MedicalWorkerDTO.convertMedicalWorkerListToDTOList(medicalWorkerService.getAllByBloodBankIsNull());
    }

    @GetMapping(value = "/getAllByBloodBank")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    public List<MedicalWorker> getAllByBloodBank(@RequestParam(value = "bloodBankId") Long id) {
        return medicalWorkerService.getAllByBloodBank(id);
    }

    @GetMapping(value = "/getBloodBankWorkingHours")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    public WorkingHours getBloodBankWorkingHours(@RequestParam(value = "id") Long id){
        return workingHoursService.getByBloodBankId(medicalWorkerService.getBloodBank(id));
    }

    @GetMapping(value = "/getBloodBankIdForMedicalWorker/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEDICALWORKER')")
    public ResponseEntity<Long> getBloodBankId(@PathVariable(value = "id") Long id) {
        try{
            return new ResponseEntity<>(medicalWorkerService.getBloodBank(id), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
