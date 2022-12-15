package group8.bloodbank.rabbitmq;

import group8.bloodbank.rabbitmq.messages.BloodRequestDeliveryDTO;
import group8.bloodbank.rabbitmq.messages.MessageDto;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionDeliveryDTO;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionResponseDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${custom.rabbitmq.exchange}")
    String exchange;

    @Value("${custom.rabbitmq.routingKey}")
    private String routingKey;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/sendNews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody MessageDto message) throws IOException {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendBloodUnitRequestResponse(BloodRequestDeliveryDTO deliveryDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, deliveryDTO);
    }

    public void sendSubscriptionsResponse(MonthlySubscriptionResponseDTO responseDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, responseDTO);
    }

    public void sendSubscriptionDelivery(MonthlySubscriptionDeliveryDTO deliveryDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, deliveryDTO);
    }
}



