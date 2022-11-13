package group8.bloodbank.controller;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.DTO.BloodBankDTO;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bloodBanks")
@CrossOrigin(origins = "http://localhost:4200")
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

    //   public BloodBank(Long id, String name, String description, double avgGrade, Map<BloodType, Double> bloodType,
    //                     ArrayList<MedicalWorker> medicalWorker, ArrayList<Item> item, ArrayList<Appointment> appointment, Address address, WorkingHours workingHours, String apiKey) {
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BloodBank> saveBloodBank(@RequestBody BloodBankDTO bloodBankDTO)  {
        BloodBank bloodBank = new BloodBank(bloodBankDTO.name, bloodBankDTO.description, bloodBankDTO.address, bloodBankDTO.image);
        try{
            bloodBank = bloodBankService.saveBloodBank(bloodBank);
            return new ResponseEntity<BloodBank>(bloodBank, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<BloodBank>(bloodBank, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/checkForBloodType")
    @ResponseBody
    public ResponseEntity<Boolean> GetAmount(@RequestParam(value = "type") String type, @RequestHeader("apiKey") String apiKey) {

        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if (b == null) {
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        boolean hasBlood = bloodBankService.getAmountOfBloodForType(BloodType.valueOf(type), b.getId());
        return new ResponseEntity<Boolean>(hasBlood, HttpStatus.OK);

    }

    @GetMapping(value = "/checkBloodAmount")
    @ResponseBody
    public ResponseEntity<Boolean> CheckBloodAmount(@RequestParam(value = "bloodType") BloodType bloodType, @RequestParam(value = "quantity") double quant, @RequestHeader("apiKey") String apiKey){
        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if(b == null){
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        boolean hasEnoughBlood = bloodBankService.CheckBloodAmount(bloodType, quant, b.getId());
        return new ResponseEntity<Boolean>(hasEnoughBlood, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/addApi")
    public void addApi(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String api = jsonObject.getString("api");
        Long id = Long.parseLong(jsonObject.getString("id"));
        bloodBankService.setApiKey(api, id);
    }

    @GetMapping(value = "/getById")
    public Optional<BloodBank> getById(@RequestParam(value = "id") Long id) {
        return bloodBankService.getById(id);
    }

    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@PathVariable String id) {
        return id;
    }
}