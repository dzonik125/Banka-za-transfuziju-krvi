package group8.bloodbank.controller;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.model.BloodUnitUrgentRequest;
import group8.bloodbank.model.DTO.BloodBankBloodDTO;
import group8.bloodbank.model.DTO.BloodBankDTO;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bloodBanks")
@CrossOrigin(origins = "http://localhost:4200")
public class BloodBankController {

    private BloodBankService bloodBankService;
    private MedicalWorkerService medicalWorkerService;

    @Autowired
    public BloodBankController(BloodBankService bloodBankService, MedicalWorkerService medicalWorkerService) {
        this.bloodBankService = bloodBankService;
        this.medicalWorkerService =medicalWorkerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllBloodBanks")
    public List<BloodBank> getAll() {
        return bloodBankService.getAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAll")
    @CrossOrigin(origins = "http://localhost:4201")
    public List<BloodBank> findAllByName(Pageable pageable){
        Pageable pageable2 = (Pageable) PageRequest.of(pageable.getNumberOfPages(), 6);
        return bloodBankService.findAllByName(pageable2);
    }


   @CrossOrigin(origins = "http://localhost:4201")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BloodBank> saveBloodBank(@RequestBody BloodBankDTO bloodBankDTO)  {
        BloodBank bloodBank = new BloodBank(bloodBankDTO.name, bloodBankDTO.description, bloodBankDTO.address, bloodBankDTO.image);
        try{
            BloodBank createdBloodBank = bloodBankService.saveBloodBank(bloodBank);
            medicalWorkerService.SetBloodBankIDsForSelectedMedicalWorkers(bloodBankDTO.medicalWorkers, bloodBank);
            return new ResponseEntity<BloodBank>(createdBloodBank, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<BloodBank>(bloodBank, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4201")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateBloodBank")
    public ResponseEntity<BloodBank> updateBloodBank(@RequestBody BloodBankDTO bloodBankDTO)  {
        BloodBank bloodBank = new BloodBank(bloodBankDTO.id, bloodBankDTO.name, bloodBankDTO.description, bloodBankDTO.address, bloodBankDTO.image, bloodBankDTO.avgGrade);
        try{
            bloodBank = bloodBankService.saveBloodBank(bloodBank);
            return new ResponseEntity<BloodBank>(bloodBank, HttpStatus.OK);
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

    @PutMapping(value = "/updateBloodAmount")
    @ResponseBody
    public void updateBloodAmount(@RequestBody BloodBank bloodBank, @RequestBody BloodType bloodType) {
        bloodBankService.updateAmountOfDonatedBlood(bloodBank, bloodType);
    }

    @GetMapping(value = "/getBloodBankBlood/{id}")
    @ResponseBody
    public ResponseEntity<BloodBankBloodDTO> getBloodBankBlood(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<BloodBankBloodDTO>(bloodBankService.getBloodBankBlood(id), HttpStatus.OK);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/urgentRequest")
    @ResponseBody
    public ResponseEntity<Boolean> SendUrgentRequest(@RequestBody BloodUnitUrgentRequest request, @RequestHeader String apiKey){
        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if(b == null){
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        boolean hasEnoughBlood = bloodBankService.sendBloodUnitsIfAvailable(request, apiKey);
        return new ResponseEntity<Boolean>(hasEnoughBlood, HttpStatus.OK);
    }


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

    @GetMapping(value = "getApiKeyById")
    public String getApiKeyById(@RequestParam(value = "id") Long id) {
        return bloodBankService.getApiKeyById(id);}
}