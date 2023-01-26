package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.AppointmentStatus;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AppointmentSlotServiceImplTest {


    @Autowired
    AppointmentSlotRepository repository;

    @Autowired
    AppointmentSlotService appointmentSlotService;

    @Autowired
    BloodBankServiceImpl bloodBankService;

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    DonorRepository donorRepository;
    @Autowired
    DonorService donorService;
    @Autowired
    MedicalWorkerService medicalWorkerService;


    @Before
    public void setUp() throws Exception {
        appointmentSlotService.saveSlot(new AppointmentSlot(0,20L,bloodBankRepository.getById(Long.valueOf(1)),donorRepository.getById(Long.valueOf(1)), LocalDateTime.now(), LocalDateTime.now().plusHours(1), AppointmentStatus.WAITING));
    }


    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {


            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                AppointmentSlot appointmentSlotToUpdate = appointmentSlotService.findById(2L);// ocitan objekat sa id 1
                //productToUpdate.setPrice(800L);// izmenjen ucitan objekat
                appointmentSlotToUpdate.setStatus(AppointmentStatus.APPROVED);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                appointmentSlotService.saveSlot(appointmentSlotToUpdate);// bacice ObjectOptimisticLockingFailureException

            }
        });
        executor.submit(new Runnable() {


            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                AppointmentSlot appointmentSlotToUpdate = appointmentSlotService.findById(2L);// ocitan isti objekat sa id 1 kao i iz prvog threada
                //productToUpdate.setPrice(900L);// izmenjen ucitan objekat
                appointmentSlotToUpdate.setStatus(AppointmentStatus.APPROVED);

                appointmentSlotService.saveSlot(appointmentSlotToUpdate);
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }



 /*   @Commit
    @Transactional
    @Test
    void scheduleAppointmentSlot() throws MessagingException, IOException, WriterException, InterruptedException {
        AppointmentSlotService appointmentSlotService = new AppointmentSlotServiceImpl(repository, bloodBankRepository, donorRepository);
        //AppointmentSlot appointmentSlot = repository.findById(Long.valueOf(1)).get();
        AppointmentSlot appointmentSlot = new AppointmentSlot(0,20L,bloodBankRepository.getById(Long.valueOf(1)),donorRepository.getById(Long.valueOf(1)), LocalDateTime.now(),LocalDateTime.now().plusHours(1), AppointmentStatus.WAITING);
        //Donor donor = donorRepository.findById(Long.valueOf(1)).get();
        //appointmentSlot.setDonor(donor);
        boolean first = appointmentSlotService.scheduleSlot(appointmentSlot);
        TimeUnit.SECONDS.sleep(5);
        boolean second = appointmentSlotService.scheduleSlot(appointmentSlot);
        assertEquals(first, true);
        assertEquals(second, false);
    }*/
}
