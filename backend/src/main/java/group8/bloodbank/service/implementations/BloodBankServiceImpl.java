package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    public List<BloodBank> getAll() {
        return null;
    }


    public BloodBank getById(Long id) {
        return null;
    }

    public double getAmountOfBloodForType(BloodType type) {
        return 0;
    }
}
