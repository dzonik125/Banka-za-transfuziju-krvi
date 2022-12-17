package group8.bloodbank.controller;


import group8.bloodbank.mapper.AppointmentCalendarMapper;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.DTO.AppointmentCallendarDTO;
import group8.bloodbank.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    private AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointmentsByBloodBankID")
    public List<AppointmentCallendarDTO> findAllAppointments(@RequestParam(value = "id") Long id) {
        return AppointmentCalendarMapper.sourceToDestination(service.findAllAppointmentsByBloodBankID(id));
    }

}
