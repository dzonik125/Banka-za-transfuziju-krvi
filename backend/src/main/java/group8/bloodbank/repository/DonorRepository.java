package group8.bloodbank.repository;

import group8.bloodbank.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Donor findByEmail(String email);

    Donor findDonorByVerificationCode(String code);

    Donor findById(int id);
}
