package group8.bloodbank.repository;

import group8.bloodbank.model.MedicalWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, Long> {

    @Query("SELECT b from MedicalWorker b where b.bloodBank is null")
    public List<MedicalWorker> getAllByBloodBankIsNull();

}
