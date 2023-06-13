package group8.bloodbank.model.DTO;

import group8.bloodbank.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentHistoryDTO {

    private Long id;
    private Long donorId;
    private Long bloodBankId;
    private Long medicalWorkerId;
    private Long appointmentId;
    private BloodType bloodType;
    private double amount;
    private AppointmentStatus status;
    public List<Item> item;
}
