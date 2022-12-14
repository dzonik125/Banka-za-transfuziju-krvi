package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    AppointmentSlotRepository repository;

    BloodBankRepository bloodBankRepository;

    @Autowired
    public AppointmentSlotServiceImpl(AppointmentSlotRepository repository, BloodBankRepository bloodBankRepository) {
        this.repository = repository;
        this.bloodBankRepository = bloodBankRepository;

        LocalDateTime starttime = LocalDateTime.now().plusHours(15);
        LocalDateTime endtime = LocalDateTime.now().plusHours(16);
        BloodBank bloodBank = bloodBankRepository.getByApiKey("123");
        Long id = Long.valueOf(1);
        AppointmentSlot a1 = new AppointmentSlot(id, bloodBank, starttime, endtime);
        repository.save(a1);
    }

    @Override
    public AppointmentSlot saveSlot(AppointmentSlot slot) {
        try {
            repository.save(slot);
            return slot;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AppointmentSlot findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<AppointmentSlot> getAll() {
        return repository.findAll();
    }
}
