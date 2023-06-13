package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.model.SubscriptionStatus;

import java.util.List;

public interface MonthlySubscriptionService {

    public List<MonthlySubscription> getAll();
    public void save(MonthlySubscription monthlySubscription);

    MonthlySubscription changeStatus(Long id, SubscriptionStatus status);
    public List<MonthlySubscription> getAllForDelivery();
}
