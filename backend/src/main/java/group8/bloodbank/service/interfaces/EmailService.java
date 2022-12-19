package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Donor;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public void sendVerificationEmail(Donor donor) throws MessagingException, UnsupportedEncodingException;
    public void sendAppointmentInformationMail(Donor donor, String url) throws MessagingException, UnsupportedEncodingException;
}
