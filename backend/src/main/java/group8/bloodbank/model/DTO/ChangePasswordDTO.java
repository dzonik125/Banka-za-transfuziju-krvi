package group8.bloodbank.model.DTO;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    public Long adminID;
    public String password;
}
