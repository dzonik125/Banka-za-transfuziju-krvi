package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();

    List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID);

    void scheduleAppointment(Appointment app) throws MessagingException, UnsupportedEncodingException;
}
