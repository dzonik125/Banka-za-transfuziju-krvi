package group8.bloodbank.model;

import group8.bloodbank.model.DTO.BloodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BloodUnitUrgentRequest {

    public Set<BloodDTO> bloodUnits;
    public String APIKey;
}
