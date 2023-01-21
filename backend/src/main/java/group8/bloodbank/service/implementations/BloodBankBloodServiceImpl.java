package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodBankBlood;
import group8.bloodbank.model.Complaint;
import group8.bloodbank.repository.BloodBankBloodRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.BloodBankBloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodBankBloodServiceImpl implements BloodBankBloodService {

    private final BloodBankBloodRepository repository;

    @Autowired
    public BloodBankBloodServiceImpl(BloodBankBloodRepository bloodBankRepository) {
        this.repository = bloodBankRepository;
    }


}
