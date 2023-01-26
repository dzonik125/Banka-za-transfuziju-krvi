package group8.bloodbank.mapper;

import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.DTO.BloodDTO;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class BloodUnitUrgentRequestMapper {

    public static HashMap<BloodType, Double> bloodUnitSetToHashMap(Set<BloodDTO> bloodDTOSet) {
        HashMap<BloodType, Double> bloodUnitHashMap = new HashMap<>();
        for(BloodDTO bloodDTO: bloodDTOSet) {
            BloodType type = getTypeFromString(bloodDTO.Type);
            bloodUnitHashMap.put(type, (double)bloodDTO.Quantity);
        }
        return  bloodUnitHashMap;
    }

    private static BloodType getTypeFromString(String bloodType) {
        BloodType forReturn = null;
        if (Objects.equals(bloodType, "ZERO_POSITIVE"))
        {
            forReturn = BloodType.Opos;

        } else if (Objects.equals(bloodType, "ZERO_NEGATIVE"))
        {
            forReturn = BloodType.Oneg;
        }
        else if (Objects.equals(bloodType, "A_POSITIVE"))
        {
            forReturn = BloodType.Apos;
        }
        else if (Objects.equals(bloodType, "A_NEGATIVE"))
        {
            forReturn = BloodType.Aneg;
        }
        else if (Objects.equals(bloodType, "B_POSITIVE"))
        {
            forReturn = BloodType.Bpos;
        }
        else if (Objects.equals(bloodType, "B_NEGATIVE"))
        {
            forReturn = BloodType.Bneg;
        }
        else if (Objects.equals(bloodType, "AB_POSITIVE"))
        {
            forReturn = BloodType.ABpos;
        }
        else if (Objects.equals(bloodType, "AB_NEGATIVE"))
        {
            forReturn = BloodType.ABneg;
        }
        return forReturn;
    }

}
