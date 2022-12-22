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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    private AppointmentService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllAppointmentsByBloodBankID")
    public List<AppointmentCallendarDTO> findAllAppointments(@RequestParam(value = "id") Long id) {
        return AppointmentCalendarMapper.sourceToDestination(service.findAllAppointmentsByBloodBankID(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAppointmentByID")
    public ResponseEntity<AppointmentDTO> findById(@RequestParam(value = "id") Long id) {
        Optional<Appointment> appointment = service.findById(id);
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        if(appointmentDTO != null)
            return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
