package group8.bloodbank.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import group8.bloodbank.model.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DonorDTO {
    public Long id;
    public String name;
    public String surname;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Belgrade")
    public LocalDateTime donationTime;
    public BloodType type;
}
