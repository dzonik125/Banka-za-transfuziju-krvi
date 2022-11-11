package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Survey;
import group8.bloodbank.repository.SurveyRepository;
import group8.bloodbank.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyServiceImpl implements SurveyService {

    SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

}
