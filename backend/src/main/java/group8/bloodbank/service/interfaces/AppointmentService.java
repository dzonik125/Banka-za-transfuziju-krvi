package group8.bloodbank.service.interfaces;

import com.google.zxing.WriterException;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();

    List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID);

    boolean scheduleAppointment(Appointment app) throws MessagingException, IOException, WriterException;

    @Cacheable("appointment")
    Appointment getById(Long id);

    @CacheEvict(cacheNames = {"appointment"}, allEntries = true)
    void removeFromCache();
}
