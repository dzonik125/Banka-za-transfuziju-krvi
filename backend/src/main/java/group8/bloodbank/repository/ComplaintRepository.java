package group8.bloodbank.repository;

import group8.bloodbank.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>, PagingAndSortingRepository<Complaint, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Complaint b set b.answer=:answer where b.id=:id")
    Void updateAnswer(@Param("id") Long id, @Param("answer") String answer);

    @Query("SELECT b from Complaint b where b.answer is null")
    List<Complaint> getAllUnanswered();
}
