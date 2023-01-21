package group8.bloodbank.model;

import com.google.common.primitives.UnsignedInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="blood_bank_blood")
public class BloodBankBlood {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer A_POSITIVE;

    @Column
    private Integer A_NEGATIVE;

    @Column
    private Integer B_POSITIVE;

    @Column
    private Integer B_NEGATIVE;

    @Column
    private Integer O_POSITIVE;

    @Column
    private Integer O_NEGATIVE;

    @Column
    private Integer AB_POSITIVE;

    @Column
    private Integer AB_NEGATIVE;

}
