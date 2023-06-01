package group8.bloodbank.repository;

import group8.bloodbank.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExaminationRepository  extends JpaRepository<MedicalExamination, Long> {
}
