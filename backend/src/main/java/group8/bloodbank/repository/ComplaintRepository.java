package group8.bloodbank.repository;

import group8.bloodbank.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>, PagingAndSortingRepository<Complaint, Long> {

}
