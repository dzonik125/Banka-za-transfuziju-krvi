package group8.bloodbank.controller;

import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.AppointmentHistoryDTO;
import group8.bloodbank.service.interfaces.AppointmentHistoryService;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointmentHistories")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentHistoryController {

    private final AppointmentHistoryService appointmentHistoryService;
    private final BloodBankService bloodBankService;

    @Autowired
    public AppointmentHistoryController(AppointmentHistoryService appointmentHistoryService, BloodBankService bloodBankService) {
        this.appointmentHistoryService = appointmentHistoryService;
        this.bloodBankService = bloodBankService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentHistoryDTO> createSlot(@RequestBody AppointmentHistoryDTO appointmentHistory){
        try{
            appointmentHistoryService.save(appointmentHistory);
            return new ResponseEntity<>(appointmentHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(appointmentHistory, HttpStatus.CONFLICT);
        }
    }

}
