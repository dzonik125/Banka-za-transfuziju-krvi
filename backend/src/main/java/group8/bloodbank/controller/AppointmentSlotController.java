package group8.bloodbank.controller;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appSlots")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentSlotController {
    private AppointmentSlotService service;

    @Autowired
    public AppointmentSlotController(AppointmentSlotService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentSlot> createSlot(@RequestBody AppointmentSlot slot){
        try{
            service.saveSlot(slot);
            return new ResponseEntity<>(slot, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(slot, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointments")
    public List<AppointmentSlot> getAll() {
        return service.getAll();
    }
}
