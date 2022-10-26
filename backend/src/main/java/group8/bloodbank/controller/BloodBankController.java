package group8.bloodbank.controller;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import java.util.List;

@RestController
@RequestMapping("/bloodBanks")
public class BloodBankController {

    private BloodBankService bloodBankService;

    @Autowired
    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BloodBank> getAll() {

        return bloodBankService.getAll();
    }

    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@PathVariable String id) {

        return id;
    }
}
