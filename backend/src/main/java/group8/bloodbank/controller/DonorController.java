package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.DonorDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.service.interfaces.AppointmentHistoryService;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/donor")
public class DonorController {

    private DonorService donorService;
    private final AppointmentHistoryService appointmentHistoryService;

    @Autowired
    public DonorController(DonorService donorService, AppointmentHistoryService appointmentHistoryService) {
        this.donorService = donorService;
        this.appointmentHistoryService = appointmentHistoryService;
    }


 /*   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donor> saveDonor(@RequestBody Donor donor)  {
        Donor savedDonor = null;
        try {
            savedDonor = donorService.saveDonor(donor);
            return new ResponseEntity<Donor>(savedDonor, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Donor>(savedDonor, HttpStatus.CONFLICT);
        }
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Donor> getAll() {
        return donorService.getAll();
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @PreAuthorize("hasRole('ROLE_MEDICALWORKER')")
    public ResponseEntity updatePenalty(@PathVariable(value = "id") Long id) {
        try{
            donorService.updatePenalty(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "findDonorByID")
    public ResponseEntity<Donor> findById(@RequestParam Long id){
        try {
            Optional<Donor> toReturn = donorService.findById(id);
            return new ResponseEntity<Donor>(toReturn.get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('ROLE_MEDICALWORKER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "findDonorsByBankId/{id}")
    public ResponseEntity<List<DonorDTO>> findByBankId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(appointmentHistoryService.getAllByBloodBankId(id), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
