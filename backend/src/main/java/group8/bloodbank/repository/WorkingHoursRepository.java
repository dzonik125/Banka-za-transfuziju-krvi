package group8.bloodbank.repository;

import group8.bloodbank.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {

    @Query("SELECT w from WorkingHours w where w.bloodBank.id=:bloodBankId")
    public WorkingHours getWorkingHoursByBloodBankId(@Param("bloodBankId") Long bloodBankId);

}
