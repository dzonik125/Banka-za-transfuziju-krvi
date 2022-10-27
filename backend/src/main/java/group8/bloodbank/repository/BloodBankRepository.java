package group8.bloodbank.repository;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

    @Query("SELECT value(m) FROM BloodBank b join b.bloodType m" +
            " where key(m) = ?1")
    public Optional<Double> getAmountOfBloodForType(BloodType type);
}
