package group8.bloodbank.model.DTO;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.MedicalWorker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class AppointmentDTO {

    public Long id;

    public List<MedicalWorker> medicalWorker;

    public Long donor_id;

    public BloodBank bloodBank;

    public LocalDateTime start;

    public double duration;

    public Donor donor;

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank newBloodBank) {
        this.bloodBank = newBloodBank;
    }
}
