package group8.bloodbank.model.DTO;

import group8.bloodbank.model.Address;
import group8.bloodbank.model.MedicalWorker;

import java.util.List;

public class BloodBankDTO {

    public long id;
    public String name;
    public String description;
    public Address address;
    public String image;
    public List<MedicalWorker> medicalWorkers;
    public double avgGrade;

    public String apiKey;
}
