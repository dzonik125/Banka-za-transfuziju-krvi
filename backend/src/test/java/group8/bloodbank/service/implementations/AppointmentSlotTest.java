package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentSlotTest {
    @Autowired
    AppointmentSlotService appointmentSlotService;

    @Autowired
    DonorRepository donorRepository;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        AppointmentSlot appointmentSlotToUpdate = appointmentSlotService.findById(1L);
        Donor d = donorRepository.findById(6L).get();
        appointmentSlotToUpdate.setDonor(d);

        //act
        Future<Boolean> firstExecutedAnswer = ((ExecutorService) executor).submit(() -> appointmentSlotService.scheduleSlot(appointmentSlotToUpdate));
        Future<Boolean> secondExecutedAnswer = ((ExecutorService) executor).submit(() -> appointmentSlotService.scheduleSlot(appointmentSlotToUpdate));

        try {
            Boolean resOffirstExecutedAnswer = firstExecutedAnswer.get();
            Boolean resultOfsecondExecutedAnswer = secondExecutedAnswer.get();
        } catch (ExecutionException e) {
            System.out.println("Exc from thread: " + e.getCause().getClass()) ;
            throw e.getCause();
        }

    }
}
