package group8.bloodbank.repository;

import group8.bloodbank.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("SELECT s from Survey s where s.donor.id=:donorId")
    public Survey getSurveyByDonorId(@Param("donorId") Long donorId);
}
