package group8.bloodbank.model.DTO;

import group8.bloodbank.model.BloodBank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentDTO {
    private List<Long> medicalWorkerIds;

    private Long donor_id;

    private BloodBank bloodBank;

    private LocalDateTime start;

    private double duration;
}
