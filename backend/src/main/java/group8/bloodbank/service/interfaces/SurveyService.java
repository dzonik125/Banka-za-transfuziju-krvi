package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.DTO.SurveyDTO;
import group8.bloodbank.model.Survey;

public interface SurveyService {
    Survey saveSurvey(SurveyDTO survey);
}
