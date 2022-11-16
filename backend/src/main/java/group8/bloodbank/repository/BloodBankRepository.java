package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

    @Query("SELECT value(m) FROM BloodBank b join b.bloodType m" +
            " where key(m) = ?1 and b.id = ?2")
    public Optional<Double> getAmountOfBloodForType(BloodType type, Long id);

    @Query("SELECT b from BloodBank b where b.apiKey = ?1")
    public BloodBank getByApiKey(String apiKey);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update BloodBank b set b.apiKey=:apiKey where b.id=:id")
    public void setApiKey(@Param("apiKey") String apiKey,@Param("id") Long id);

    @Query("SELECT value(m) FROM BloodBank b join b.bloodType m" +
            " where key(m) = ?1 and b.id = ?2")
    public Optional<Double> CheckBloodAmount(BloodType type, Long id);

    @Query("SELECT b.apiKey from BloodBank b where b.id=:id")
    public String getApiKeyById(@Param("id") Long id);
}
