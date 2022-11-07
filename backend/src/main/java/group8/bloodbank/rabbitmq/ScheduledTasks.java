package group8.bloodbank.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableScheduling
public class ScheduledTasks {

    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public ScheduledTasks(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        MessageDto message = new MessageDto("Random message from Java client: " + UUID.randomUUID().toString(), "Ovo je neki tekst veliki u kom stoje informacije");
        rabbitMQSender.send(message);
        System.out.println("Sent :" + message.toString());
    }
}
