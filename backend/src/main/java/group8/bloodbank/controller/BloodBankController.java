package group8.bloodbank.controller;

import blood.Blood;
import com.fasterxml.jackson.databind.util.JSONPObject;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/checkForBloodType")
    @ResponseBody

    public ResponseEntity<Boolean> getAmount(@RequestParam(value = "type") String type, @RequestHeader("apiKey") String apiKey) {

        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if (b == null) {
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        boolean hasBlood = bloodBankService.getAmountOfBloodForType(BloodType.valueOfLabel(type), b.getId());
        return new ResponseEntity<Boolean>(hasBlood, HttpStatus.OK);

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
