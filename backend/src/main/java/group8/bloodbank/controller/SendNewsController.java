package group8.bloodbank.controller;

import group8.bloodbank.model.User;
import group8.bloodbank.rabbitmq.RabbitMQSender;
import group8.bloodbank.service.interfaces.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendNews")
public class SendNewsController {

    private RabbitMQSender rabbitMQSender;

    @Autowired
    public SendNewsController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }


  /*  @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user)  {
        MessageDto savedUser = null;
        try {
            savedUser = rabbitMQSender.send(message);
            return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(savedUser, HttpStatus.CONFLICT);
        }
    }*/
}
