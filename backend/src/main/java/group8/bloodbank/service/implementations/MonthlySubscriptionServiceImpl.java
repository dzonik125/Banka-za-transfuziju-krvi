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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    }

    @Override
    public List<MonthlySubscription> getAll() {
        return monthlySubscriptionRepository.findAll();
    }

    @Override
    public void save(MonthlySubscription monthlySubscription) {
        monthlySubscriptionRepository.save(monthlySubscription);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public MonthlySubscription changeStatus(Long id, SubscriptionStatus status) {
        MonthlySubscription monthlySubscription = monthlySubscriptionRepository.findById(id).get();
        MonthlySubscriptionResponseDTO monthlySubscriptionResponseDTO = new MonthlySubscriptionResponseDTO();
        monthlySubscriptionResponseDTO.hospitalSubscriptionId = monthlySubscription.getHospitalSubscriptionId();
        if(status == SubscriptionStatus.APPROVED) {
            monthlySubscriptionResponseDTO.status = SubscriptionResponseStatus.ACCEPTED;
        }else {
            monthlySubscriptionResponseDTO.status = SubscriptionResponseStatus.REJECTED;
        }
        monthlySubscription.setStatus(status);
        save(monthlySubscription);
        rabbitMQSender.sendSubscriptionsResponse(monthlySubscriptionResponseDTO);
        return monthlySubscription;
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
