package group8.bloodbank.service.interfaces;

import com.google.zxing.WriterException;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();

    List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID);

    boolean scheduleAppointment(Appointment app) throws MessagingException, IOException, WriterException;
}
