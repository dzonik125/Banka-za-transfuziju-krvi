package group8.bloodbank.service.implementations;

import group8.bloodbank.mapper.BloodUnitUrgentRequestMapper;
import group8.bloodbank.model.*;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class BloodBankServiceImpl implements BloodBankService {


    private final BloodBankRepository bloodBankRepository;

    @Autowired
    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;

    }

    public List<BloodBank> getAll() {
        return bloodBankRepository.findAll();
    }


    public Optional<BloodBank> getById(Long id) {
        return bloodBankRepository.findById(id);
    }

    public void updateBloodBank(BloodBank b) {
        bloodBankRepository.save(b);
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

    @Override
    public BloodBank saveBloodBank(BloodBank bloodBank) {
        return bloodBankRepository.save(bloodBank);
    }


    @Override
    public boolean sendBloodUnitsIfAvailable(BloodUnitUrgentRequest bloodUnitUrgentRequest, String apiKey) {
        HashMap<BloodType, Double> bloodUnits =  BloodUnitUrgentRequestMapper.bloodUnitSetToHashMap(bloodUnitUrgentRequest.getBloodUnits());
        return checkIfBloodUnitsAvailable(bloodUnits, apiKey);
    }



    @Override
    public boolean checkIfBloodUnitsAvailable(HashMap<BloodType, Double> bloodUnits, String apiKey) {
        List<Object[]> bloodUnitsInBank = bloodBankRepository.getAllBloodUnits(apiKey);
        HashMap<BloodType, Double> bloodUnitsInBankMap = new HashMap<>();
        for(Object[] map : bloodUnitsInBank) {
            bloodUnitsInBankMap.put((BloodType)map[0], (double)map[1]);
        }
        for(BloodType key : bloodUnits.keySet()) {
            if (bloodUnits.get(key) > bloodUnitsInBankMap.get(key)) {
                return false;
            }else {
                bloodUnitsInBankMap.replace(key, bloodUnitsInBankMap.get(key) - bloodUnits.get(key));
            }
        }
        BloodBank bloodBank = getByApiKey(apiKey);
        bloodBank.setBloodType(bloodUnitsInBankMap);
        updateBloodBank(bloodBank);
        return true;
    }

    @Override
    public String getApiKeyById(Long id) {
        return bloodBankRepository.getApiKeyById(id);
    }

    @Override
    public BloodBank getByName(String bb) {
        return bloodBankRepository.findByName(bb);
    }

    @Override
    public List<BloodBank> findAllByName(Pageable pageable){
        return bloodBankRepository.findAllByName(pageable);
    }

    public void updateBloodAmount(MedicalExamination med) {
        BloodBank bloodBank = bloodBankRepository.findById(med.getBloodBank().getId()).get();
        BloodBankBlood bloodBankBlood = bloodBank.getBlood();

        switch(med.getBloodType()) {
            case "A_POSITIVE":
                bloodBankBlood.setA_POSITIVE(bloodBankBlood.getA_POSITIVE() + med.getAmount());
                break;
            case "A_NEGATIVE":
                bloodBankBlood.setA_NEGATIVE(bloodBankBlood.getA_NEGATIVE() + med.getAmount());
                break;

            case "B_POSITIVE":
                bloodBankBlood.setB_POSITIVE(bloodBankBlood.getB_POSITIVE() + med.getAmount());
                break;

            case "B_NEGATIVE":
                bloodBankBlood.setB_NEGATIVE(bloodBankBlood.getB_NEGATIVE() + med.getAmount());
                break;

            case "AB_POSITIVE":
                bloodBankBlood.setAB_POSITIVE(bloodBankBlood.getAB_POSITIVE() + med.getAmount());
                break;

            case "AB_NEGATIVE":
                bloodBankBlood.setAB_NEGATIVE(bloodBankBlood.getAB_NEGATIVE() + med.getAmount());
                break;

            case "O_NEGATIVE":
                bloodBankBlood.setO_NEGATIVE(bloodBankBlood.getO_NEGATIVE() + med.getAmount());
                break;

            case "O_POSITIVE":
                bloodBankBlood.setO_POSITIVE(bloodBankBlood.getO_POSITIVE() + med.getAmount());
                break;

        }

        bloodBank.setBlood(bloodBankBlood);
        bloodBankRepository.save(bloodBank);
    }
}
