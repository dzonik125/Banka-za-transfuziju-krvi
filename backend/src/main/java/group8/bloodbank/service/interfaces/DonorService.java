package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Donor;
import group8.bloodbank.model.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface DonorService {
    Donor saveDonor(Donor donor);

    List<Donor> getAll();
}
