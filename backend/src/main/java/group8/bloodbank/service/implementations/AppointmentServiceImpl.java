package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.repository.AppointmentRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.repository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAll() {
        return repository.findAll();
    }
}
