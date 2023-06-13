package group8.bloodbank.rabbitmq.messages;


import group8.bloodbank.model.DTO.BloodDTO;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

public class MonthlySubscriptionMessageDTO {
    public List<BloodDTO> RequestedBlood;
    public String DeliveryDate;
    public String BloodBankAPIKey;
    public int HospitalSubscriptionId;
}
