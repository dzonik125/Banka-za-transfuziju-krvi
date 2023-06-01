package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.model.SubscriptionStatus;
import group8.bloodbank.rabbitmq.messages.MonthlySubscriptionResponseDTO;
import group8.bloodbank.rabbitmq.RabbitMQSender;
import group8.bloodbank.rabbitmq.messages.SubscriptionResponseStatus;
import group8.bloodbank.repository.MonthlySubscriptionRepository;
import group8.bloodbank.service.interfaces.MonthlySubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class MonthlySubscriptionServiceImpl implements MonthlySubscriptionService {

    private final MonthlySubscriptionRepository monthlySubscriptionRepository;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    public MonthlySubscriptionServiceImpl(MonthlySubscriptionRepository monthlySubscriptionRepository) {
        this.monthlySubscriptionRepository = monthlySubscriptionRepository;
//        HashMap<BloodType, Double> map = new HashMap<>();
//        map.put(BloodType.Opos, 12.0);
//        map.put(BloodType.Bpos, 12.0);
//        MonthlySubscription monthlySubscription = new MonthlySubscription(1l, 1, map, LocalDate.now(),null, SubscriptionStatus.WAITING);
//        save(monthlySubscription);
    }

    @Override
    public List<MonthlySubscription> getAll() {
        return monthlySubscriptionRepository.findAll();
    }

    @Override
    public void save(MonthlySubscription monthlySubscription) {
        monthlySubscriptionRepository.save(monthlySubscription);
    }

    @Override
    public void changeStatus(MonthlySubscription monthlySubscription) {
        MonthlySubscriptionResponseDTO monthlySubscriptionResponseDTO = new MonthlySubscriptionResponseDTO();
        monthlySubscriptionResponseDTO.hospitalSubscriptionId = monthlySubscription.getHospitalSubscriptionId();
        if(monthlySubscription.getStatus() == SubscriptionStatus.APPROVED) {
            monthlySubscriptionResponseDTO.status = SubscriptionResponseStatus.ACCEPTED;
        }else {
            monthlySubscriptionResponseDTO.status = SubscriptionResponseStatus.REJECTED;
        }
        save(monthlySubscription);
        rabbitMQSender.sendSubscriptionsResponse(monthlySubscriptionResponseDTO);
    }

    @Override
    public List<MonthlySubscription> getAllForDelivery() {
        List<MonthlySubscription> monthlySubscriptions = new ArrayList<>();
        for(MonthlySubscription monthlySubscription : getAll()) {
            if((monthlySubscription.getDeliveryDate().isEqual(LocalDate.now())
                    || monthlySubscription.getDeliveryDate().isBefore(LocalDate.now()))
                    && (monthlySubscription.getStatus() == SubscriptionStatus.APPROVED)) {
                monthlySubscriptions.add(monthlySubscription);
            }
        }
        return monthlySubscriptions;
    }
}
