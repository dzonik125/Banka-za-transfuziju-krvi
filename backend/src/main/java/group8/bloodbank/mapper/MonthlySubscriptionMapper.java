package group8.bloodbank.mapper;

import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.DTO.BloodDTO;
import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.model.SubscriptionStatus;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionMessageDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class MonthlySubscriptionMapper {

    public  static MonthlySubscription toModel(MonthlySubscriptionMessageDTO monthlySubscriptionMessageDTO) {
        MonthlySubscription subscription = new MonthlySubscription();
        HashMap<BloodType, Double> bloodUnits = new HashMap<>();
        for(BloodDTO bloodDTO : monthlySubscriptionMessageDTO.RequestedBlood) {
            BloodType type = BloodUnitUrgentRequestMapper.getTypeFromString(bloodDTO.Type);
            bloodUnits.put(type, (double)bloodDTO.Quantity);
        }
        subscription.setBloodUnits(bloodUnits);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(monthlySubscriptionMessageDTO.DeliveryDate, formatter);
        subscription.setDeliveryDate(localDate);
        subscription.setStatus(SubscriptionStatus.WAITING);
        subscription.setHospitalSubscriptionId(monthlySubscriptionMessageDTO.HospitalSubscriptionId);
        return subscription;
    }
}
