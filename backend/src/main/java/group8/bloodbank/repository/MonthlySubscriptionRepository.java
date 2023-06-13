package group8.bloodbank.repository;

import group8.bloodbank.model.MonthlySubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlySubscriptionRepository extends JpaRepository<MonthlySubscription, Long> {

}
