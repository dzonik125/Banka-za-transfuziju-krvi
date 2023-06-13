package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Item;
import group8.bloodbank.repository.EquipmentRepository;
import group8.bloodbank.service.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Item> findAllByBloodBankId(Long id) {
        return equipmentRepository.findAllByBloodBankID(id);
    }
}
