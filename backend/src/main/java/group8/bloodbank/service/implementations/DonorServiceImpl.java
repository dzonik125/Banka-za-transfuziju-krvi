package group8.bloodbank.service.implementations;

import group8.bloodbank.model.*;
import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.EmailService;
import group8.bloodbank.service.interfaces.RoleService;
import group8.bloodbank.service.interfaces.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    DonorRepository donorRepository;

    @Autowired
    AppointmentSlotRepository appointmentSlotRepository;
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;


    @Autowired
    public DonorServiceImpl() {
    }

    @Override
    public Donor registerDonor(UserDTO userDTO) throws MessagingException, UnsupportedEncodingException {

        Donor d = new Donor();

        d = (Donor) userService.saveUser(d,userDTO);

        List<Role> roles = roleService.findByName("ROLE_DONOR");
        d.setRoles(roles);
        d.setPoints(0);
        d.setCategory(Category.REGULAR);
        d.setPenalty(0);
        d.setBloodType(BloodType.ABneg);
        //d.setComplaint(Collections.emptySet());
        //d.setSurvey(Collections.emptySet());

        d.setEnabled(false);

        d.setVerificationCode(RandomString.make(64));
        d.setHasSurvey(false);

        donorRepository.save(d);
        emailService.sendVerificationEmail(d);

        return d;
    }



    @Override
    public List<Donor> getAll() {
        return donorRepository.findAll();
    }

    @Override
    public void updatePenalty(Donor donor) {
        donor.setPenalty(donor.getPenalty() + 1);
        donorRepository.save(donor);
    }

    @Override
    public Optional<Donor> findById(Long id) {
        return donorRepository.findById(id);
    }

    @Override
    public boolean canSchedule(AppointmentSlot appointmentSlot) {
        if (appointmentSlot.getStartTime().isBefore(LocalDateTime.now())) {
            throw new UnsupportedOperationException("This appointment has expired !");
        }
        for (AppointmentSlot app : appointmentSlotRepository.findAppointmentSlotByDonor_Id(appointmentSlot.donor.getId())) {
            if (appointmentSlot.bloodBank.getId().equals(app.bloodBank.getId()) && appointmentSlot.getStartTime().equals(app.getStartTime()))
                throw new UnsupportedOperationException("You already have an appointment at this facility at this time !");
        }

        return true;
    }

    @Override
    public boolean verify(String code) {
        Donor donor = donorRepository.findDonorByVerificationCode(code);

        if (donor == null || donor.isEnabled()) {
            return false;
        } else {
            donor.setVerificationCode(null);
            donor.setEnabled(true);
            donorRepository.save(donor);
            return true;
        }

    }

    public Donor getById(Long donor_id) {
        return donorRepository.findById(donor_id).get();
    }

    @Override
    public Donor getByEmail(String email) {
        return donorRepository.findByEmail(email);
    }

}
