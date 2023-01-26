package group8.bloodbank.repository;

import group8.bloodbank.model.Admin;
import group8.bloodbank.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Admin b set b.password=:password, b.firstLogin=false where b.id=:adminID")
    void updateAdmin(@Param("adminID") Long adminID, @Param("password") String password);

}
