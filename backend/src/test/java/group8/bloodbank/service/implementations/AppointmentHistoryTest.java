package group8.bloodbank.service.implementations;

import group8.bloodbank.model.*;
import group8.bloodbank.model.DTO.AppointmentHistoryDTO;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentHistoryService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentHistoryTest {
    @Autowired
    AppointmentHistoryService appointmentHistoryService;

    @Autowired
    MedicalWorkerService medicalWorkerService;

    @Autowired
    DonorRepository donorRepository;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        AppointmentHistoryDTO appHistory = new AppointmentHistoryDTO(1l, 1l, 1l, 2l, 1l, BloodType.ABpos, 0.4, AppointmentStatus.FINISHED, new ArrayList<Item>());
        AppointmentHistoryDTO appHistory2 = new AppointmentHistoryDTO(2l, 6l, 1l, 2l, 2l, BloodType.ABpos, 0.4, AppointmentStatus.FINISHED, new ArrayList<Item>());
        //act
        Future<AppointmentHistory> firstExecutedAnswer = ((ExecutorService) executor).submit(() -> appointmentHistoryService.save(appHistory));
        Future<AppointmentHistory> secondExecutedAnswer = ((ExecutorService) executor).submit(() -> appointmentHistoryService.save(appHistory2));

        executor.submit(() -> {
            System.out.println("Startovan Thread 1");
            appointmentHistoryService.save(appHistory);

        });
        Future<?> future2 = executor.submit(() -> {
            System.out.println("Startovan Thread 2");
            appointmentHistoryService.save(appHistory);
        });
        try {
            future2.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}