package group8.bloodbank.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BloodBankBloodDTO {

    private Long id;

    private double A_POSITIVE;

    private double A_NEGATIVE;

    private double B_POSITIVE;

    private double B_NEGATIVE;

    private double O_POSITIVE;

    private double O_NEGATIVE;

    private double AB_POSITIVE;

    private double AB_NEGATIVE;

}
