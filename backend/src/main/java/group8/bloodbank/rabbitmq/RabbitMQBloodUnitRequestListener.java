package group8.bloodbank.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import group8.bloodbank.mapper.BloodUnitRequestMapper;
import group8.bloodbank.model.BloodUnitRequest;
import group8.bloodbank.rabbitmq.messages.BloodUnitRequestMessageDTO;
import group8.bloodbank.service.interfaces.BloodUnitRequestService;
import group8.bloodbank.taskScheduler.BloodUnitSupplierScheduler;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RabbitMQBloodUnitRequestListener implements MessageListener {

    @Autowired
    private  BloodUnitRequestService bloodUnitRequestService;

    @Autowired
    private BloodUnitSupplierScheduler bloodUnitSupplierScheduler;


    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BloodUnitRequestMessageDTO bloodUnitRequestMessageDTO = objectMapper.readValue(message.getBody(), BloodUnitRequestMessageDTO.class);
            saveBloodRequest(bloodUnitRequestMessageDTO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveBloodRequest(BloodUnitRequestMessageDTO bloodUnitRequestMessageDTO) throws IOException {
        BloodUnitRequest bloodUnitRequest= BloodUnitRequestMapper.toModel(bloodUnitRequestMessageDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(bloodUnitRequestMessageDTO.getDeliveryDate(), formatter);
        if(localDate.isEqual(LocalDate.now())) {
            bloodUnitSupplierScheduler.checkForBloodInBanks(bloodUnitRequest);
        }
        bloodUnitRequestService.save(bloodUnitRequest);
    }


}
