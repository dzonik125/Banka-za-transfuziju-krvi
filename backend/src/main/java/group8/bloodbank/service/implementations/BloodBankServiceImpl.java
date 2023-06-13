package group8.bloodbank.service.implementations;

import group8.bloodbank.mapper.BloodUnitUrgentRequestMapper;
import group8.bloodbank.model.*;
import group8.bloodbank.model.DTO.BloodBankBloodDTO;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    public static final double BLOOD_DONATION_AMOUNT = 0.4;
    private final BloodBankRepository bloodBankRepository;
    private final EquipmentService equipmentService;

    @Value("${custom.rabbitmq.bloodRequestRoutingKey}")
    private String requestQueue;

    @Autowired
    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository,
                                EquipmentService equipmentService) {
        this.bloodBankRepository = bloodBankRepository;
        this.equipmentService = equipmentService;

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
    public void updateAmountOfDonatedBlood(BloodBank bloodBank, BloodType bloodType) {
        double currentAmount = bloodBank.getBloodType().get(bloodType);
        bloodBank.getBloodType().replace(bloodType, currentAmount + BLOOD_DONATION_AMOUNT);
        updateBloodBank(bloodBank);
    }

    @Override
    public void updateEquipmentStorage(BloodBank bloodBank, List<Item> items) {
        List<Item> bloodBankItems = equipmentService.findAllByBloodBankId(bloodBank.getId());
        for(Item item : bloodBankItems) {
            for(Item usedItem : items) {
                if(item.getId() == usedItem.getId()) {
                    int updatedQuantity = item.getQuantity() - usedItem.getQuantity();
                    if(updatedQuantity >= 0) {
                        item.setQuantity(updatedQuantity);
                    }else {
                        throw new ArithmeticException();
                    }
                }
            }
        }
        bloodBank.setItem(bloodBankItems);
        updateBloodBank(bloodBank);
    }

    public BloodBank getByName(String bb) {
        return bloodBankRepository.findByName(bb);
    }

    public List<BloodBank> getAllRegisteredToRequestQueue() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        for(BloodBank bloodBank: getAll()) {
            if(bloodBank.getHospitalBloodRequestsRoutingKey().equals(requestQueue)) {
                bloodBanks.add(bloodBank);
            }
        }
        return bloodBanks;
    }

    @Override
    public BloodBankBloodDTO getBloodBankBlood(Long bloodBankId) {
        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).get();
        Map<BloodType, Double> bloodMap = bloodBank.getBloodType();
        BloodBankBloodDTO bloodBankBlood = new BloodBankBloodDTO();
        for(BloodType type : bloodMap.keySet()) {
            switch(type) {
                case Apos:
                    bloodBankBlood.setA_POSITIVE(bloodBankBlood.getA_POSITIVE() + bloodMap.get(type));
                    break;
                case Aneg:
                    bloodBankBlood.setA_NEGATIVE(bloodBankBlood.getA_NEGATIVE() + bloodMap.get(type));
                    break;

                case Bpos:
                    bloodBankBlood.setB_POSITIVE(bloodBankBlood.getB_POSITIVE() + bloodMap.get(type));
                    break;

                case Bneg:
                    bloodBankBlood.setB_NEGATIVE(bloodBankBlood.getB_NEGATIVE() + bloodMap.get(type));
                    break;

                case ABpos:
                    bloodBankBlood.setAB_POSITIVE(bloodBankBlood.getAB_POSITIVE() + bloodMap.get(type));
                    break;

                case ABneg:
                    bloodBankBlood.setAB_NEGATIVE(bloodBankBlood.getAB_NEGATIVE() + bloodMap.get(type));
                    break;

                case Opos:
                    bloodBankBlood.setO_NEGATIVE(bloodBankBlood.getO_POSITIVE() + bloodMap.get(type));
                    break;

                case Oneg:
                    bloodBankBlood.setO_POSITIVE(bloodBankBlood.getO_NEGATIVE() + bloodMap.get(type));
                    break;

            }
        }
        return bloodBankBlood;
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
