package group8.bloodbank.model.DTO;

import clojure.spec.gen.alpha$gen_for_name;
import group8.bloodbank.model.Address;

import group8.bloodbank.model.BloodBank;

import group8.bloodbank.model.MedicalWorker;

import java.util.ArrayList;
import java.util.List;

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

    public BloodBank bloodBank;
    public String fullname;


    public MedicalWorkerDTO(MedicalWorker mw) {
        this.id = mw.getId();
        this.name = mw.getName();
        this.surname = mw.getSurname();
        this.password = mw.getPassword();
        this.jmbg = mw.getJmbg();
        this.occupation = mw.getOccupation();
        this.address = mw.getAddress();
        this.gender = mw.getGender().toString();
        this.email = mw.getEmail();
        this.fullname = mw.getName() + ' ' + mw.getSurname();
    }

    public static List<MedicalWorkerDTO> convertMedicalWorkerListToDTOList(List<MedicalWorker> medicalWorkerList) {
        List<MedicalWorkerDTO> ret = new ArrayList<>();
        for(MedicalWorker mw: medicalWorkerList) {
            ret.add(new MedicalWorkerDTO(mw));
        }
        return ret;
    }
}