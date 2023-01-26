package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.MedicalWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, Long> {

    @Query("SELECT b from MedicalWorker b where b.bloodBank is null")
    public List<MedicalWorker> getAllByBloodBankIsNull();

    @Query("SELECT b from MedicalWorker b where b.bloodBank.id =:bloodBankId")
    public List<MedicalWorker> getAllByBloodBank(@Param("bloodBankId") Long bloodBankId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update MedicalWorker b set b.bloodBank=:bloodBank where b.id=:medicalWorkerID")
    void updateMedicalWorker(@Param("medicalWorkerID") Long medicalWorkerID, @Param("bloodBank") BloodBank bloodBank);

    @Query("SELECT b.bloodBank.id from MedicalWorker b where b.id=:id")
    public Long getBloodBank(@Param("id") Long id);
}
