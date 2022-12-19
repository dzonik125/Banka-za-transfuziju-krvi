package group8.bloodbank.repository;

import group8.bloodbank.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a from Appointment a where a.bloodBank.id=:bloodBankId")
    public List<Appointment> findAllByBloodBankID(@Param("bloodBankId") Long bloodBankId);

}
