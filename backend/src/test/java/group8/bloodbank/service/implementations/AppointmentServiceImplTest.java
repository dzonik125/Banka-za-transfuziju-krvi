package group8.bloodbank.service.implementations;

import com.google.zxing.WriterException;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.repository.AppointmentRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.EmailService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AppointmentServiceImplTest {

    @Autowired
    AppointmentRepository repository;
    @Autowired
    BloodBankServiceImpl bloodBankService;
    @Autowired
    DonorService donorService;
    @Autowired
    EmailService emailService;
    @Autowired
    MedicalWorkerService medicalWorkerService;

    @Commit
    @Transactional
    @Test
    void scheduleAppointment() throws MessagingException, IOException, WriterException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        AppointmentService appointmentService = new AppointmentServiceImpl(repository, bloodBankService, donorService, emailService);
        Appointment appointment = new Appointment(15L, medicalWorkerService.getAll(), Long.parseLong("1"), bloodBankService.getById(Long.parseLong("1")).get(), start, 30.0, donorService.getAll().get(0));
        Appointment appointment2 = new Appointment(30L, medicalWorkerService.getAll(), Long.parseLong("1"), bloodBankService.getById(Long.parseLong("1")).get(), start, 30.0, donorService.getAll().get(1));

        boolean first = appointmentService.scheduleAppointment(appointment);
        TimeUnit.SECONDS.sleep(5);
        boolean second = appointmentService.scheduleAppointment(appointment2);
        assertEquals(first, true);
        assertEquals(second, false);
    }
}