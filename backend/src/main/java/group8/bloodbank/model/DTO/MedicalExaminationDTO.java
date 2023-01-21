package group8.bloodbank.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import group8.bloodbank.model.Address;
import group8.bloodbank.model.Gender;
import lombok.Data;

@Data
public class MedicalExaminationDTO {
    private int amount;
    private String bloodType;
    private String email;
    private String bb;

}
