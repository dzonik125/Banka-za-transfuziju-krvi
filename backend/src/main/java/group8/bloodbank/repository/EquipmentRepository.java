package group8.bloodbank.repository;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i from Item i where i.bloodBank.id=:bloodBankId")
    public List<Item> findAllByBloodBankID(@Param("bloodBankId") Long bloodBankId);
}
