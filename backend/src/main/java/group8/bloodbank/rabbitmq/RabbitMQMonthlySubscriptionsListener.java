package group8.bloodbank.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import group8.bloodbank.mapper.MonthlySubscriptionMapper;
import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionMessageDTO;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.MonthlySubscriptionService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RabbitMQMonthlySubscriptionsListener implements MessageListener {

    @Autowired
    private MonthlySubscriptionService monthlySubscriptionService;

    @Autowired
    private BloodBankService bloodBankService;

    @Override
    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MonthlySubscriptionMessageDTO monthlySubscriptionMessageDTO = objectMapper.readValue(message.getBody(), MonthlySubscriptionMessageDTO.class);
            saveMonthlySubscription(monthlySubscriptionMessageDTO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMonthlySubscription(MonthlySubscriptionMessageDTO monthlySubscriptionMessageDTO) {
        MonthlySubscription subscription =  MonthlySubscriptionMapper.toModel(monthlySubscriptionMessageDTO);
        subscription.setBloodBank(bloodBankService.getByApiKey(monthlySubscriptionMessageDTO.BloodBankAPIKey));
        monthlySubscriptionService.save(subscription);
    }
}
