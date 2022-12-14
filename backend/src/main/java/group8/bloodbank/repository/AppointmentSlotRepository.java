package group8.bloodbank.repository;

import group8.bloodbank.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
}
