package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.DTO.SurveyDTO;
import group8.bloodbank.model.Survey;

import java.util.List;

public interface SurveyService {
    Survey saveSurvey(SurveyDTO survey);

    List<Survey> findAll();

    Survey getByDonorId(long id);

    boolean canDonorDonate(Long donorId);

    void updateSurvey(SurveyDTO surveyDTO);
}
