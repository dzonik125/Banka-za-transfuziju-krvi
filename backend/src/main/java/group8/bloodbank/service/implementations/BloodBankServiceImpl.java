package group8.bloodbank.service.implementations;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BloodBankServiceImpl implements BloodBankService {


    private BloodBankRepository bloodBankRepository;

    @Autowired
    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
        Map<BloodType, Double> map1 = new HashMap<>();
        map1.put(BloodType.Apos, 222.5);
        map1.put(BloodType.Aneg, 0.0);
        Map<BloodType, Double> map2 = new HashMap<>();
        map2.put(BloodType.Bneg, 0.0);
        map2.put(BloodType.Bpos, 0.0);
        BloodBank b1 = new BloodBank(1l, "Banka 1", "", 3, map1, null, null
                , null, null, null, "1");
        BloodBank b2 = new BloodBank(2l, "Banka 2", "", 3, map2, null, null
                , null, null, null, "");
        bloodBankRepository.save(b1);
        bloodBankRepository.save(b2);
    }

    public List<BloodBank> getAll() {
        return null;
    }


    public BloodBank getById(Long id) {
        return null;
    }

    @Override
    public boolean getAmountOfBloodForType(BloodType type, Long id) {
        Optional<Double> b = bloodBankRepository.getAmountOfBloodForType(type, id);
        if(b.isPresent()) {
            System.out.println(b.get() > 0);
            return (b.get() > 0);
        }
        return false;
    }

    @Override
    public BloodBank getByApiKey(String apiKey) {
        return bloodBankRepository.getByApiKey(apiKey);

    }
    @Override
    public void setApiKey(String apiKey, Long id){
        bloodBankRepository.setApiKey(apiKey, id);
    }

    @Override
    public boolean CheckBloodAmount(BloodType type, double quant, Long id){
        Optional<Double> b = bloodBankRepository.CheckBloodAmount(type, id);
        if(b.isPresent()) {
            return (b.get() >= quant);
        }
        return false;
    }
}
