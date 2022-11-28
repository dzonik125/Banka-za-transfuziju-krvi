package group8.bloodbank.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import group8.bloodbank.model.Address;
import group8.bloodbank.model.Gender;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @JsonProperty
    private Address address;
    private String jmbg;
    private String occupation;
    private Gender gender;
}
