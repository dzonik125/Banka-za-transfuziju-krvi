package group8.bloodbank.service.implementations;

import group8.bloodbank.model.WorkingHours;
import group8.bloodbank.repository.WorkingHoursRepository;
import group8.bloodbank.service.interfaces.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingHoursServiceImpl implements WorkingHoursService {

    WorkingHoursRepository repository;

    @Autowired
    public WorkingHoursServiceImpl(WorkingHoursRepository repo){
        this.repository = repo;
    }

    @Override
    public WorkingHours getByBloodBankId(Long id) {
        return repository.getWorkingHoursByBloodBankId(id);
    }
}
