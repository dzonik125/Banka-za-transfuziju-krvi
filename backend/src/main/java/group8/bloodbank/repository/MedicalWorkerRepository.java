package group8.bloodbank.repository;

import group8.bloodbank.model.Donor;
import group8.bloodbank.model.MedicalWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalWorkerRepository extends JpaRepository<MedicalWorker, Long> {
}
