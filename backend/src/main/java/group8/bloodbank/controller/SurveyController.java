package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.SurveyDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.Survey;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    private DonorService donorService;


    @Autowired
    public SurveyController (SurveyService surveyService, DonorService donorService) {
        this.surveyService = surveyService;
        this.donorService = donorService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<Survey> createSurvey(@RequestBody SurveyDTO survey)  {
        Survey savedSurvey = null;
        try {
            Optional<Donor> d = donorService.findById(Long.valueOf(survey.getDonor()));
            if(d.get().getHasSurvey()) {
                    surveyService.updateSurvey(survey);
            }else
                savedSurvey = surveyService.saveSurvey(survey);
            return new ResponseEntity<Survey>(savedSurvey, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Survey>(savedSurvey, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAll")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public List<Survey> getAll() {
        return surveyService.findAll();
    }
}
