package group8.bloodbank.controller;

import blood.Blood;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/checkForBloodType")
    @ResponseBody
    public ResponseEntity<Boolean> getAmount(@RequestParam(value = "type") String type, @RequestHeader("apiKey") String apiKey) {

        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if(b == null) {
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        boolean hasBlood  = bloodBankService.getAmountOfBloodForType(BloodType.valueOfLabel(type), b.getId());
        return  new ResponseEntity<Boolean>(hasBlood, HttpStatus.OK);
    }

    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@PathVariable String id) {
        return id;
    }
}
