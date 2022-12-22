package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> getAll();
    List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID);

    Optional<Appointment> findById(Long id);
}
