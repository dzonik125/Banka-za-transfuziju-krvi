package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Complaint;
import group8.bloodbank.service.interfaces.ComplaintService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplaintTests {

    @Autowired
    private ComplaintService complaintService;


    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void transactionAnswerComplaintTest() throws Throwable {

        //arrange
        Executor executor = Executors.newFixedThreadPool(2);

        //act
        Future<Complaint> firstExecutedAnswer = ((ExecutorService) executor).submit(() -> complaintService.answerComplaint(1L, "answ 1"));
        Future<Complaint> secondExecutedAnswer = ((ExecutorService) executor).submit(() -> complaintService.answerComplaint(1L, "answ 2"));

        Complaint resultOffirstExecutedAnswer = firstExecutedAnswer.get();

        //assert
        assertEquals(resultOffirstExecutedAnswer.getAnswer().equals("answ 1"), true);
        try {
            Complaint resultOfsecondExecutedAnswer = secondExecutedAnswer.get();
        } catch (ExecutionException e) {
            System.out.println("Exc from thread: " + e.getCause().getClass()) ;
            throw e.getCause();
        }

    }
}
