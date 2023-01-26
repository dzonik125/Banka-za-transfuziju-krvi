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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

        ExecutorService executor = Executors.newFixedThreadPool(2);
        final boolean[] first = {false};
        final boolean[] second = {true};
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan thread 1");
                try {
                    first[0] = appointmentService.scheduleAppointment(appointment);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan thread 2");
                try{Thread.sleep(150);} catch (InterruptedException e) {}
                try {
                    second[0] = appointmentService.scheduleAppointment(appointment2);
                    assertEquals(second[0], false);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}