package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.repository.AppointmentRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository repository;
    BloodBankServiceImpl bloodBankService;
    DonorService donorService;
    EmailService emailService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, BloodBankServiceImpl bloodBankService, DonorService donorService, EmailService emailService) {
        this.repository = appointmentRepository;
        this.bloodBankService = bloodBankService;
        this.donorService = donorService;
        this.emailService = emailService;
    }

    @Override
    public List<Appointment> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID) {
        List<Appointment> appointmentsForBloodBankID = repository.findAllByBloodBankID(bloodBankID);

        for (Appointment appointment: appointmentsForBloodBankID) {
            appointment.setDonor(donorService.getById(appointment.donor_id));
        }

        return  appointmentsForBloodBankID;
    }

    @Override

    public Optional<Appointment> findById(Long id) {
        return repository.findById(id);
    }

    public void scheduleAppointment(Appointment app) throws MessagingException, UnsupportedEncodingException {
        repository.save(app);
        emailService.sendAppointmentMail(app);

    }
}
