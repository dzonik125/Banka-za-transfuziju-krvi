package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.Donor;

import java.util.List;


public interface DonorService {
    Donor saveDonor(UserDTO donor);
    List<Donor> getAll();

    Donor getById(Long donor_id);
}
