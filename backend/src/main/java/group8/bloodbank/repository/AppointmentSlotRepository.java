package group8.bloodbank.repository;

import group8.bloodbank.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    public List<AppointmentSlot> findAppointmentSlotByDonor_Id(Long donor_id);
}
