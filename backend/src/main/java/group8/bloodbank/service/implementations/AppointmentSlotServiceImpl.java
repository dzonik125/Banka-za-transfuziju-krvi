package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    AppointmentSlotRepository repository;
    BloodBankRepository bloodBankRepository;

    DonorRepository donorRepository;

    @Autowired
    public AppointmentSlotServiceImpl(AppointmentSlotRepository repository, BloodBankRepository bloodBankRepository, DonorRepository donorRepository) {
        this.repository = repository;
        this.bloodBankRepository = bloodBankRepository;
        this.donorRepository = donorRepository;
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
