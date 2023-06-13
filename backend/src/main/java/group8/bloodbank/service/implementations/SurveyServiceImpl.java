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

import java.util.List;
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
        d.get().setHasSurvey(true);
        userRepository.save(d.get());

        s.setMenstrualCycle(survey.isMenstrualCycle());
        s.setDentalInterventions(survey.isDentalInterventions());
        s.setSkinIllness(survey.isSkinIllness());
        s.setLessThan50kg(survey.isLessThan50kg());
        s.setSicknessSymptoms(survey.isSicknessSymptoms());
        s.setTherapyIntake(survey.isTherapyIntake());
        s.setBloodPressureAbnormalities(survey.isBloodPressureAbnormalities());
        s.setSkinPiercings(survey.isSkinPiercings());
        s.setDonor(d.get());
        return surveyRepository.save(s);
    }

    @Override
    public List<Survey> findAll() {
       return surveyRepository.findAll();
    }

    @Override
    public Survey getByDonorId(long id) {
        return surveyRepository.getSurveyByDonorId(id);
    }

    @Override
    public boolean canDonorDonate(Long donorId) {
        Survey donorSurvey = surveyRepository.getSurveyByDonorId(donorId);
        if(donorSurvey != null) {
            if(donorSurvey.isBloodPressureAbnormalities() ||
               donorSurvey.isLessThan50kg() ||
               donorSurvey.isSkinPiercings() ||
               donorSurvey.isMenstrualCycle() ||
               donorSurvey.isTherapyIntake() ||
               donorSurvey.isDentalInterventions() ||
               donorSurvey.isSicknessSymptoms() ||
               donorSurvey.isSkinIllness()) {
                return false;
            }else  {
                return true;
            }
        }return false;
    }

    @Override
    public void updateSurvey(SurveyDTO surveyDTO) {
        Survey forUpdate = surveyRepository.getSurveyByDonorId(Long.valueOf(surveyDTO.getDonor()));
        Optional<Donor> d = donorRepository.findById(Long.valueOf(surveyDTO.getDonor()));
        forUpdate.setDonor(d.get());
        forUpdate.setMenstrualCycle(surveyDTO.isMenstrualCycle());
        forUpdate.setDentalInterventions(surveyDTO.isDentalInterventions());
        forUpdate.setSkinIllness(surveyDTO.isSkinIllness());
        forUpdate.setLessThan50kg(surveyDTO.isLessThan50kg());
        forUpdate.setSicknessSymptoms(surveyDTO.isSicknessSymptoms());
        forUpdate.setTherapyIntake(surveyDTO.isTherapyIntake());
        forUpdate.setBloodPressureAbnormalities(surveyDTO.isBloodPressureAbnormalities());
        forUpdate.setSkinPiercings(surveyDTO.isSkinPiercings());
        forUpdate.setDonor(d.get());
        surveyRepository.save(forUpdate);

    }


}
