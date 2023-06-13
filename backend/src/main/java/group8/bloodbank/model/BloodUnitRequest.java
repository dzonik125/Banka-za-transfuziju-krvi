package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class BloodUnitRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int hospitalRequestId;
    @Column
    private BloodType type;
    @Column
    private double amountL;
    @Column
    private LocalDate deliveryDate;
    @Column
    private BloodUnitRequestStatus status;
}
