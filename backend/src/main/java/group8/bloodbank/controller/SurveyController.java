package group8.bloodbank.controller;


import group8.bloodbank.model.Survey;
import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.SurveyService;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private SurveyService surveyService;

    @Autowired
    public SurveyController (SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Survey> saveUser(@RequestBody Survey survey)  {
        Survey savedSurvey = null;
        try {
            savedSurvey = surveyService.saveSurvey(survey);
            return new ResponseEntity<Survey>(savedSurvey, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Survey>(savedSurvey, HttpStatus.CONFLICT);
        }
    }
}