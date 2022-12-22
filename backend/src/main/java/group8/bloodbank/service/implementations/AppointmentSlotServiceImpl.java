package group8.bloodbank.service.implementations;

import com.google.zxing.WriterException;
import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.AppointmentStatus;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import group8.bloodbank.service.interfaces.EmailService;
import group8.bloodbank.service.interfaces.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {


    private final String link = "http://localhost:4200/scheduledAppointments";
    AppointmentSlotRepository repository;
    BloodBankRepository bloodBankRepository;
    DonorRepository donorRepository;

    EmailService emailService;
    QRCodeService qrCodeService;


    @Autowired
    public AppointmentSlotServiceImpl(AppointmentSlotRepository repository, BloodBankRepository bloodBankRepository, DonorRepository donorRepository, QRCodeService qrCodeService, EmailService emailService) {
        this.repository = repository;
        this.bloodBankRepository = bloodBankRepository;
        this.donorRepository = donorRepository;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
    }

    @Override
    public AppointmentSlot saveSlot(AppointmentSlot slot) {
        try {
            repository.save(slot);
            return slot;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AppointmentSlot scheduleSlot(AppointmentSlot slot) throws MessagingException, IOException, WriterException {
        Optional<Donor> d = donorRepository.findById(slot.donor.getId());
        if(slot.getStartTime().isBefore(LocalDateTime.now())){
            throw new UnsupportedOperationException("This appointment has expired !");
        } else{
            for (AppointmentSlot app: repository.findAppointmentSlotByDonor_Id(slot.donor.getId())) {
                if(slot.bloodBank.getId().equals(app.bloodBank.getId()) && slot.getStartTime().equals(app.getStartTime()))
                    throw new UnsupportedOperationException("You already have an appointment at this facility at this time !");
            }
            repository.save(slot);
            //String open = LocalDateTime.now() + " Dear " + slot.donor.getName() + ", you have an appointment scheduled for " + slot.getStartTime() + " at the " + slot.bloodBank.getName();
            //String url = qrCodeService.generateImageAsQRCode(open, 200, 200, slot.getId().toString());
            //emailService.sendAppointmentInformationMail(d.get(), url);
        }
        return slot;
    }

    @Override
    public AppointmentSlot cancelSlot(AppointmentSlot slot) {

            if(LocalDateTime.now().plusDays(1).isBefore(slot.getStartTime())){
                slot.setStatus(AppointmentStatus.WAITING);
                slot.setDonor(null);
                repository.save(slot);
            } else{
                throw new UnsupportedOperationException();
            }
            return slot;
    }

    @Override
    public AppointmentSlot findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<AppointmentSlot> getAll() {
        return repository.findAll();
    }

    @Override
    public List<AppointmentSlot> getAllByBankId(Long id) {
        return repository.getAllbyBankId(id);
    }

}
