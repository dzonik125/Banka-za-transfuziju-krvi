package group8.bloodbank.model.DTO;

import group8.bloodbank.model.Address;
import group8.bloodbank.model.BloodBank;

public class MedicalWorkerDTO {

    public Long id;
    public String name;
    public String surname;
    public String password;
    public String jmbg;
    public String occupation;
    public Address address;
    public String gender;
    public String email;
    public String fullname = name + ' ' + surname;
    public BloodBank bloodBank;

}