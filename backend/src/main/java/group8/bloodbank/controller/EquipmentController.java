package group8.bloodbank.controller;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Item;
import group8.bloodbank.service.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllByBloodBankId")
    public List<Item> getAllByBloodBankId(@RequestParam Long id) {
        return equipmentService.findAllByBloodBankId(id);
    }

}
