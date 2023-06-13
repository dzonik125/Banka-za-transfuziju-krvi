package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Complaint;
import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.model.SubscriptionStatus;
import group8.bloodbank.service.interfaces.ComplaintService;
import group8.bloodbank.service.interfaces.MonthlySubscriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonthlySubscriptionTest {

    @Autowired
    private MonthlySubscriptionService monthlySubscriptionService;


    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void transactionAnswerComplaintTest() throws Throwable {

        //arrange
        Executor executor = Executors.newFixedThreadPool(2);

        //act
        Future<MonthlySubscription> firstExecutedAnswer = ((ExecutorService) executor).submit(() -> monthlySubscriptionService.changeStatus(1l, SubscriptionStatus.REJECTED));
        Future<MonthlySubscription> secondExecutedAnswer = ((ExecutorService) executor).submit(() -> monthlySubscriptionService.changeStatus(1l, SubscriptionStatus.APPROVED));

        try {
            MonthlySubscription mo = secondExecutedAnswer.get();
            MonthlySubscription mo2 = firstExecutedAnswer.get();
        } catch (ExecutionException e) {
            System.out.println("Exc from thread: " + e.getCause().getClass()) ;
            throw e.getCause();
        }

    }
}