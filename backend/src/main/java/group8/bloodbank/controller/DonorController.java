package group8.bloodbank.controller;

import group8.bloodbank.model.Donor;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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



   /* @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<User> findById(@RequestParam Long id){
        try {
            User toReturn = userService.findById(id);
            return new ResponseEntity<User>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }*/
}
