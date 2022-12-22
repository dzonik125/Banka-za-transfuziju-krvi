package group8.bloodbank.controller;

import group8.bloodbank.model.Donor;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/donor")
public class DonorController {

    private DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Donor> getAll() {
        return donorService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePenalty(@RequestBody Donor donor) {
        donorService.updatePenalty(donor);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "findDonorByID")
    public ResponseEntity<Donor> findById(@RequestParam Long id){
        try {
            Optional<Donor> toReturn = donorService.findById(id);
            return new ResponseEntity<Donor>(toReturn.get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
