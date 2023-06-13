package group8.bloodbank.repository;
import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {
    @Query("SELECT a from AppointmentHistory a where a.bloodBank.id=:id")
    public List<AppointmentHistory> getAllByBankId(@Param("id") Long id);
}
