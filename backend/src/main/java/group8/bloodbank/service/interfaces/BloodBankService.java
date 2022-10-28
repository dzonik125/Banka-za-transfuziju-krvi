package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;

import java.util.List;
import java.util.Optional;

public interface BloodBankService {

    public List<BloodBank> getAll();
    public BloodBank getById(Long id);
    public Optional<Double> getAmountOfBloodForType(BloodType type);

}
