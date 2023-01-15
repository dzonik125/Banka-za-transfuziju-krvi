package group8.bloodbank.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import group8.bloodbank.model.AppointmentStatus;
import group8.bloodbank.model.BloodBank;

import java.time.LocalDateTime;

public class AppointmentPreviewDTO {

    public String bloodBankName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime endTime;

    public String donorNameAndSurname;
}
