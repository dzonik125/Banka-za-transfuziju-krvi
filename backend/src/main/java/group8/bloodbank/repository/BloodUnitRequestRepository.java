package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodUnitRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodUnitRequestRepository extends JpaRepository<BloodUnitRequest, Long> {

}
