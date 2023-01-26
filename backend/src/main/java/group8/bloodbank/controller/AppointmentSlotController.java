package group8.bloodbank.controller;

import group8.bloodbank.mapper.AppointmentPreviewMapper;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.DTO.AppointmentPreviewDTO;
import group8.bloodbank.model.DTO.AppointmentSlotDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appSlots")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentSlotController {
    private AppointmentSlotService service;
    private AppointmentService appointmentService;

    private DonorService donorService;

    private BloodBankService bloodBankService;
    private final DonorRepository donorRepository;
    private final AppointmentSlotRepository appointmentSlotRepository;

    @Autowired
    public AppointmentSlotController(AppointmentSlotService service, DonorService donorService, BloodBankService bloodBankService,
                                     DonorRepository donorRepository, AppointmentService appointmentService,
                                     AppointmentSlotRepository appointmentSlotRepository) {
        this.service = service;
        this.donorService = donorService;
        this.bloodBankService = bloodBankService;
        this.appointmentService = appointmentService;
        this.donorRepository = donorRepository;
        this.appointmentSlotRepository = appointmentSlotRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_MEDICALWORKER')")
    public ResponseEntity<AppointmentSlot> createSlot(@RequestBody AppointmentSlot slot){
        List<AppointmentSlot> slots = service.getAll();
        for(AppointmentSlot s : slots) {
            if(slot.getStartTime().isBefore(s.getEndTime()) && slot.getStartTime().isAfter(s.getStartTime().minusMinutes(1))){
                if(s.getBloodBank().getId() == slot.getBloodBank().getId()){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            } else if (slot.getEndTime().isAfter(s.getStartTime()) && slot.getEndTime().isBefore(s.getEndTime().plusMinutes(1))){
                if(s.getBloodBank().getId() == slot.getBloodBank().getId()){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            } else if (slot.getStartTime().isEqual(s.getStartTime()) && slot.getEndTime().isEqual(s.getEndTime())){
                if(s.getBloodBank().getId() == slot.getBloodBank().getId()){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        }
        try{
            service.saveSlot(slot);
            return new ResponseEntity<>(slot, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(slot, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getById")
    public AppointmentPreviewDTO getById(@RequestParam(value = "id") Long id) {
        return AppointmentPreviewMapper.sourceToDestination(service.getById(id).get());
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
            try {
                donorService.canSchedule(slot);
                service.scheduleSlot(slot);
                return new ResponseEntity<Exception>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/getAvailableBanks")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<List<BloodBank>> getFreeBanks(@RequestBody LocalDateTime start){
        boolean addToRet = true;
        List<BloodBank> toRet = new ArrayList<>();
        for (BloodBank b: bloodBankService.getAll()
             ) {
            addToRet = true;
            for (AppointmentSlot as: service.getAllByBankId(b.getId())
                 ) {
                if(as.getStartTime().minusMinutes(1).isBefore(start) && as.getEndTime().plusMinutes(1).isAfter(start)){
                    addToRet = false;
                    break;
                }
            }
            for (Appointment a: appointmentService.findAllAppointmentsByBloodBankID(b.getId())
                 ) {
                if(a.getStart().minusMinutes(1).isBefore(start) && a.getStart().plusMinutes((long) a.getDuration()).plusMinutes(1).isAfter(start)){
                    addToRet = false;
                    break;
                }
                if(a.getStart().minusMinutes(1).isBefore(start.plusMinutes(30)) && a.getStart().plusMinutes((long) a.getDuration()).plusMinutes(1).isAfter(start.plusMinutes(30))) {
                    addToRet = false;
                    break;
                }
            }
            if(addToRet){
                toRet.add(b);
            }
        }
        return new ResponseEntity<List<BloodBank>>(toRet, HttpStatus.OK);
    }
}
