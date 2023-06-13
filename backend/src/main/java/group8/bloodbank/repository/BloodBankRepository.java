package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long>, PagingAndSortingRepository<BloodBank, Long> {

    @Query("SELECT value(m) FROM BloodBank b join b.bloodType m" +
            " where key(m) = ?1 and b.id = ?2")
    Optional<Double> getAmountOfBloodForType(BloodType type, Long id);

    @Query("SELECT key(m), value(m) FROM BloodBank b join b.bloodType m where b.apiKey=:apiKey")
    List<Object[]> getAllBloodUnits(@Param("apiKey") String apiKey);

    @Query("SELECT b from BloodBank b where b.apiKey = ?1")
    BloodBank getByApiKey(String apiKey);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update BloodBank b set b.apiKey=:apiKey where b.id=:id")
    void setApiKey(@Param("apiKey") String apiKey, @Param("id") Long id);

    @Query("SELECT value(m) FROM BloodBank b join b.bloodType m" +
            " where key(m) = ?1 and b.id = ?2")
    Optional<Double> CheckBloodAmount(BloodType type, Long id);

    @Query("SELECT b.apiKey from BloodBank b where b.id=:id")
    String getApiKeyById(@Param("id") Long id);

    List<BloodBank> findAllByName(Pageable pageable);

    BloodBank findByName(String Name);
}
