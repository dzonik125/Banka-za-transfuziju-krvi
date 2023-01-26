package group8.bloodbank.service.implementations;

import com.google.zxing.WriterException;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.AppointmentRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository repository;
    BloodBankServiceImpl bloodBankService;
    DonorService donorService;
    EmailService emailService;

    private final ReentrantLock lock = new ReentrantLock();

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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean scheduleAppointment(Appointment app) throws MessagingException, javax.mail.MessagingException, IOException, WriterException {
        lock.lock();
        try {
            for (Appointment a:
                 getAll()) {
                if(a.getStart().isEqual(app.getStart()) && a.getBloodBank().getId() == app.getBloodBank().getId()){
                    return false;
                }
            }
            repository.save(app);
            emailService.sendAppointmentMail(app);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Appointment getById(Long id) {
        return repository.findById(id).get();
    }
}
