package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;

import java.util.List;
import java.util.Optional;

public interface BloodBankService {

    public List<BloodBank> getAll();
    public BloodBank getById(Long id);
    public boolean getAmountOfBloodForType(BloodType type, Long id);
    public void setApiKey(String apiKey, Long id);
    public BloodBank getByApiKey(String apiKey);
    public boolean CheckBloodAmount (BloodType type, double quant, Long id);
}
