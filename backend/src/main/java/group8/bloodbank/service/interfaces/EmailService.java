package group8.bloodbank.service.interfaces;

import com.google.type.DateTime;
import com.google.zxing.WriterException;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.Donor;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public void sendVerificationEmail(Donor donor) throws MessagingException, UnsupportedEncodingException;
    public void sendAppointmentInformationMail(Donor donor, String url) throws MessagingException, UnsupportedEncodingException;
    @Async
    void sendAppointmentMail(Appointment appointment) throws MessagingException, IOException, WriterException;
}
