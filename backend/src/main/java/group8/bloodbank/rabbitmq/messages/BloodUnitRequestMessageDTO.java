package group8.bloodbank.rabbitmq.messages;

import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.BloodUnitRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BloodUnitRequestMessageDTO {
    private int hospitalRequestId;
    private String type;
    private double amountL;
    private String deliveryDate;
}
