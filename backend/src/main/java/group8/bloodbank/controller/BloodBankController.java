package group8.bloodbank.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping(value = "/check")
    @ResponseBody
    public boolean getAmount(@RequestParam(value = "type") String type, @RequestParam(value = "bankID") int id) {
        System.out.println(type);
        Optional<Double> a = bloodBankService.getAmountOfBloodForType(BloodType.valueOfLabel(type), id);
        double b = a.get();
        if (b > 0)
            return true;
        else
            return false;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/addApi")
    public void addApi(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String api = jsonObject.getString("api");
        Long id = Long.parseLong(jsonObject.getString("id"));
        bloodBankService.setApiKey(api, id);
    }

//    @GetMapping(value = "/checkAmount")
//    @ResponseBody
//    public boolean getSpecificAmount(@RequestParam(value = "type") String type) {
//
//    }

    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@PathVariable String id) {
        return id;
    }
}
