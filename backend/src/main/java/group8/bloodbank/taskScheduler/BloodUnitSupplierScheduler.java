package group8.bloodbank.taskScheduler;

import groovy.util.logging.Slf4j;
import group8.bloodbank.model.*;
import group8.bloodbank.rabbitmq.RabbitMQSender;
import group8.bloodbank.rabbitmq.messages.BloodRequestDeliveryDTO;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionDeliveryDTO;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.BloodUnitRequestService;
import group8.bloodbank.service.interfaces.MonthlySubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class BloodUnitSupplierScheduler {

    @Autowired
    private BloodBankService bloodBankService;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private BloodUnitRequestService bloodUnitRequestService;

    @Autowired
    private MonthlySubscriptionService monthlySubscriptionService;

    //@Scheduled(cron ="0 0 9 * * *")
    @Scheduled(cron = "*/10 * * * * *")
    public void supplyBloodIfNeeded() throws IOException {
        List<BloodUnitRequest> bloodUnitRequestList = bloodUnitRequestService.getAllWaitingForDelivery();
        for(BloodUnitRequest request : bloodUnitRequestList) {
            checkForBloodInBanks(request);
        }
    }

    //@Scheduled(cron ="0 0 9 * * *")
    @Scheduled(cron = "*/10 * * * * *")
    public void checkForMonthlySubscriptions() {
        List<MonthlySubscription> monthlySubscriptions = monthlySubscriptionService.getAllForDelivery();
        for(MonthlySubscription monthlySubscription : monthlySubscriptions) {
            boolean hasBlood = bloodBankService.checkIfBloodUnitsAvailable(new HashMap<BloodType, Double>(monthlySubscription.getBloodUnits()), monthlySubscription.getBloodBank().getApiKey());
            MonthlySubscriptionDeliveryDTO deliveryDTO = new MonthlySubscriptionDeliveryDTO();
            deliveryDTO.hospitalSubscriptionId = monthlySubscription.getHospitalSubscriptionId();
            deliveryDTO.delivered = hasBlood;
            monthlySubscription.setDeliveryDate(monthlySubscription.getDeliveryDate().plusMonths(1));
            monthlySubscriptionService.save(monthlySubscription);
            rabbitMQSender.sendSubscriptionDelivery(deliveryDTO);
        }
    }

    public void checkForBloodInBanks(BloodUnitRequest request) throws IOException {
        List<BloodBank> bloodBanks = bloodBankService.getAllRegisteredToRequestQueue();
        HashMap<BloodType, Double> bloodUnit = new HashMap<>();
        bloodUnit.put(request.getType(), request.getAmountL());
        for(BloodBank bank : bloodBanks) {
            if(bloodBankService.checkIfBloodUnitsAvailable(bloodUnit, bank.getApiKey())) {
                request.setStatus(BloodUnitRequestStatus.APPROVED);
                bloodUnitRequestService.save(request);
                sendBloodRequestResponse(request, true);
                return;
            }
            request.setStatus(BloodUnitRequestStatus.REJECTED);
            bloodUnitRequestService.save(request);
            sendBloodRequestResponse(request, false);
        }
    }

    public void sendBloodRequestResponse(BloodUnitRequest request, boolean delivered) {
        BloodRequestDeliveryDTO bloodRequestDeliveryDTO = new BloodRequestDeliveryDTO();
        bloodRequestDeliveryDTO.hospitalRequestId = request.getHospitalRequestId();
        bloodRequestDeliveryDTO.delivered = delivered;
        rabbitMQSender.sendBloodUnitRequestResponse(bloodRequestDeliveryDTO);
    }
}
