package group8.bloodbank.rabbitmq;

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

    @CrossOrigin(origins = "http://localhost:4201")
    @PostMapping(value = "/sendNews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody MessageDto message) throws IOException {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}



