package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodBankBlood;
import group8.bloodbank.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BloodBankBloodRepository extends JpaRepository<BloodBankBlood, Long>{

//    @Query("SELECT b from BloodBankBlood b where b. = ?1")
//    public BloodBankBlood findByBloodBankId(Long bloodBankId);
}
