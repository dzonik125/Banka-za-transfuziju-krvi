package group8.bloodbank.controller;

import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.service.interfaces.AppointmentHistoryService;
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

    @Autowired
    public AppointmentHistoryController(AppointmentHistoryService appointmentHistoryService) {
        this.appointmentHistoryService = appointmentHistoryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentHistory> createSlot(@RequestBody AppointmentHistory appointmentHistory){
        try{
            appointmentHistoryService.save(appointmentHistory);
            return new ResponseEntity<>(appointmentHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(appointmentHistory, HttpStatus.CONFLICT);
        }
    }

}
