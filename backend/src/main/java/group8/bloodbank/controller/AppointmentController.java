package group8.bloodbank.controller;


import group8.bloodbank.mapper.AppointmentCalendarMapper;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.AppointmentCallendarDTO;
import group8.bloodbank.model.DTO.AppointmentDTO;
import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.AppointmentService;
import org.modelmapper.ModelMapper;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    private AppointmentService service;
    private MedicalWorkerService medicalWorkerService;
    private DonorService donorService;



    public AppointmentController(AppointmentService service, MedicalWorkerService medicalWorkerService, DonorService donorService) {

        this.service = service;
        this.medicalWorkerService = medicalWorkerService;
        this.donorService = donorService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointmentsByBloodBankID")
    public List<AppointmentCallendarDTO> findAllAppointments(@RequestParam(value = "id") Long id) {
        return AppointmentCalendarMapper.sourceToDestination(service.findAllAppointmentsByBloodBankID(id));
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAppointmentByID")
    public ResponseEntity<Appointment> findById(@RequestParam(value = "id") Long id) {
        Optional<Appointment> appointment = service.findById(id);
        if (appointment.isPresent())
            return new ResponseEntity<Appointment>(appointment.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/scheduleAppointment")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity scheduleAppointment(@RequestBody AppointmentDTO app){
        try {
            List<MedicalWorker> medicalWorkers = new ArrayList<>();
            for (Long id: app.getMedicalWorkerIds()
                 ) {
                medicalWorkers.add(medicalWorkerService.findById(id));
            }
            Appointment a = new Appointment(medicalWorkers, app.getDonor_id(), app.getBloodBank(), app.getStart(), app.getDuration(), donorService.findById(app.getDonor_id()).get());
            service.scheduleAppointment(a);
            return new ResponseEntity<>(a, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

}
