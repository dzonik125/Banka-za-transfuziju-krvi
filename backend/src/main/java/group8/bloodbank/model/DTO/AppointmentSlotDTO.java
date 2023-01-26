package group8.bloodbank.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import group8.bloodbank.model.AppointmentStatus;
import group8.bloodbank.model.BloodBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSlotDTO {


    public Long id;

    public BloodBank bloodBank;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public LocalDateTime startTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public LocalDateTime endTime;

    public AppointmentStatus status;

    public Long donor;


}
