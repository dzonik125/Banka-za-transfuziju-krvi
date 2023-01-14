package group8.bloodbank.controller;


import group8.bloodbank.mapper.AppointmentCalendarMapper;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.DTO.AppointmentCallendarDTO;
import group8.bloodbank.model.DTO.AppointmentDTO;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.model.WorkingHours;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import group8.bloodbank.service.interfaces.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    private AppointmentService service;
    private MedicalWorkerService medicalWorkerService;
    private DonorService donorService;

    private WorkingHoursService workingHoursService;

    @Autowired
    public AppointmentController(AppointmentService service, MedicalWorkerService medicalWorkerService, DonorService donorService, WorkingHoursService workingHoursService) {
        this.service = service;
        this.medicalWorkerService = medicalWorkerService;
        this.donorService = donorService;
        this.workingHoursService = workingHoursService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointmentsByBloodBankID")
    public List<AppointmentCallendarDTO> findAllAppointments(@RequestParam(value = "id") Long id) {
        return AppointmentCalendarMapper.sourceToDestination(service.findAllAppointmentsByBloodBankID(id));
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
            if(!service.scheduleAppointment(a))
                return new ResponseEntity(HttpStatus.CONFLICT);
            return new ResponseEntity<>(a, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getBloodBankWorkingHours")
    public WorkingHours getBloodBankWorkingHours(@RequestParam(value = "id") Long id){
        return workingHoursService.getByBloodBankId(id);
    }

}
