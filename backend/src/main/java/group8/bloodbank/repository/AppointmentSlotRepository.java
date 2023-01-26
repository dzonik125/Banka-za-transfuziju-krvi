package group8.bloodbank.repository;

import group8.bloodbank.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    public List<AppointmentSlot> findAppointmentSlotByDonor_Id(Long donor_id);

    @Query("SELECT a from AppointmentSlot a where a.bloodBank.id=:id")
    public List<AppointmentSlot> getAllbyBankId(@Param("id") Long id);
}
