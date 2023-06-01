package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.MonthlySubscription;

import java.util.List;

public interface MonthlySubscriptionService {

    public List<MonthlySubscription> getAll();
    public void save(MonthlySubscription monthlySubscription);

    void changeStatus(MonthlySubscription monthlySubscription);
    public List<MonthlySubscription> getAllForDelivery();
}
