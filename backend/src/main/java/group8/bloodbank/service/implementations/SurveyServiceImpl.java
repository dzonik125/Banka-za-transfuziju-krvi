package group8.bloodbank.service.implementations;

import group8.bloodbank.model.DTO.SurveyDTO;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.Survey;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.repository.SurveyRepository;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    SurveyRepository surveyRepository;

    @Autowired
    DonorRepository donorRepository;
    private final UserRepository userRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository,
                             UserRepository userRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Survey saveSurvey(SurveyDTO survey) {

        Survey s = new Survey();
        Optional<Donor> d = donorRepository.findById(Long.valueOf(survey.getDonor()));
        d.get().setHaveSurvey(true);
        userRepository.save(d.get());

        s.setAnswer1(survey.getAnswer1());
        s.setAnswer2(survey.getAnswer2());
        s.setAnswer3(survey.getAnswer3());
        s.setAnswer4(survey.getAnswer4());
        s.setAnswer5(survey.getAnswer5());
        s.setAnswer6(survey.getAnswer6());
        s.setAnswer7(survey.getAnswer7());
        s.setAnswer8(survey.getAnswer8());
        s.setAnswer9(survey.getAnswer9());
        s.setAnswer10(survey.getAnswer10());
        s.setDonor(d.get());
        return surveyRepository.save(s);
    }


}
