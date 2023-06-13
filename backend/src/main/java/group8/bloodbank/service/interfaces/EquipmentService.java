package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Item;

import java.util.List;

public interface EquipmentService {
    public List<Item> findAllByBloodBankId(Long id);
}
