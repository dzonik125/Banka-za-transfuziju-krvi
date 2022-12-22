package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Appointment;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> getAll();
    Optional<Appointment> findById(Long id);
    List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID);
    void scheduleAppointment(Appointment app) throws MessagingException, UnsupportedEncodingException;
}
