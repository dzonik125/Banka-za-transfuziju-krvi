package group8.bloodbank.controller;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.AppointmentSlotDTO;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public List<AppointmentSlotDTO> getAllAppointments() {
        List<AppointmentSlotDTO> appointmentSlots = new ArrayList<AppointmentSlotDTO>();
        for (AppointmentSlot as: service.getAll()) {
            AppointmentSlotDTO aps = new AppointmentSlotDTO(as.getId(), as.getBloodBank(), as.getStartTime(), as.getEndTime(), as.getStatus());
            appointmentSlots.add(aps);
        }
        return appointmentSlots;
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateAppointment")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<AppointmentSlot> update(@RequestBody AppointmentSlotDTO appointmentSlot)  {
        AppointmentSlot as = new AppointmentSlot(appointmentSlot.getId(), appointmentSlot.getBloodBank(), appointmentSlot.getStartTime(), appointmentSlot.getEndTime(), appointmentSlot.getStatus());
        try{
            as = service.saveSlot(as);
            return new ResponseEntity<AppointmentSlot>(as, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<AppointmentSlot>(as, HttpStatus.CONFLICT);
        }
    }
}
