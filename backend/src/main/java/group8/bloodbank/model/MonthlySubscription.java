package group8.bloodbank.model;

import group8.bloodbank.model.DTO.BloodDTO;
import jdk.jshell.JShell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class MonthlySubscription {
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int hospitalSubscriptionId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="bloodType_monthlySubscription", joinColumns=@JoinColumn(name="monthlySubscription_id"))
    @MapKeyColumn(name = "bloodType_key")
    @Column(name="bloodType_amount")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<BloodType, Double> bloodUnits;

    @Column
    private LocalDate deliveryDate;

    @OneToOne(fetch = FetchType.EAGER)
    private BloodBank bloodBank;

    @Column
    private SubscriptionStatus status;

}
