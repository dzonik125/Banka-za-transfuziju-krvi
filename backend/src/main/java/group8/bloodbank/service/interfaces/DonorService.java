package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.Donor;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


public interface DonorService {
    Donor registerDonor(UserDTO donor) throws MessagingException, UnsupportedEncodingException;
    List<Donor> getAll();

    void updatePenalty(Long donorId);

    Optional<Donor> findById(Long id);

    boolean canSchedule(AppointmentSlot appointmentSlot);

    boolean verify(String code);

    Donor getById(Long donor_id);

    Donor getByEmail(String email);
}
