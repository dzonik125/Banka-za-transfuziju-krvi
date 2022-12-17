package group8.bloodbank.controller;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.AppointmentSlotDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appSlots")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentSlotController {
    private AppointmentSlotService service;

    private DonorService donorService;

    private BloodBankService bloodBankService;
    private final DonorRepository donorRepository;

    @Autowired
    public AppointmentSlotController(AppointmentSlotService service, DonorService donorService, BloodBankService bloodBankService,
                                     DonorRepository donorRepository) {
        this.service = service;
        this.donorService = donorService;
        this.bloodBankService = bloodBankService;

        this.donorRepository = donorRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentSlot> createSlot(@RequestBody AppointmentSlot slot){
        List<AppointmentSlot> slots = service.getAll();
        for(AppointmentSlot s : slots) {
            if(slot.getStartTime().isBefore(s.getEndTime()) && slot.getStartTime().isAfter(s.getStartTime().minusMinutes(1))){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (slot.getEndTime().isAfter(s.getStartTime()) && slot.getEndTime().isBefore(s.getEndTime().plusMinutes(1))){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (slot.getStartTime().isEqual(s.getStartTime()) && slot.getEndTime().isEqual(s.getEndTime())){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        try{
            service.saveSlot(slot);
            return new ResponseEntity<>(slot, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(slot, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointments")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public List<AppointmentSlot> getAllAppointments() {
        return service.getAll();
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateAppointment")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<Exception> update(@RequestBody AppointmentSlotDTO appointmentSlot)  {
        Optional<Donor> donor = donorRepository.findById(appointmentSlot.donor);
        AppointmentSlot slot = new AppointmentSlot(appointmentSlot.id, appointmentSlot.bloodBank, donor.get(), appointmentSlot.startTime, appointmentSlot.endTime, appointmentSlot.status);
        try{
            service.scheduleSlot(slot);
            return new ResponseEntity<Exception>(HttpStatus.OK);
        } catch (Exception e){
            //e.printStackTrace();
            return new ResponseEntity<Exception>(e, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/cancelAppointment")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<AppointmentSlot> cancelAppointment(@RequestBody AppointmentSlotDTO appointmentSlot)  {
        Optional<Donor> donor = donorRepository.findById(appointmentSlot.donor);
        AppointmentSlot slot = new AppointmentSlot(appointmentSlot.id, appointmentSlot.bloodBank, donor.get(), appointmentSlot.startTime, appointmentSlot.endTime, appointmentSlot.status);
        try{
            service.cancelSlot(slot);
            return new ResponseEntity<AppointmentSlot>(slot, HttpStatus.OK);
        } catch (Exception e){
            //e.printStackTrace();
            return new ResponseEntity<AppointmentSlot>(slot, HttpStatus.CONFLICT);
        }
    }
}
