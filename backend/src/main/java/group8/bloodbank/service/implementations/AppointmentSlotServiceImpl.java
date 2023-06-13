package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.AppointmentStatus;
import group8.bloodbank.repository.AppointmentSlotRepository;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.AppointmentSlotService;
import group8.bloodbank.service.interfaces.EmailService;
import group8.bloodbank.service.interfaces.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppointmentSlotServiceImpl implements AppointmentSlotService {


    private final String link = "http://localhost:4200/scheduledAppointments";
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AppointmentSlot saveSlot(AppointmentSlot slot) {
        try {
            repository.save(slot);
            return slot;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean scheduleSlot(AppointmentSlot slot) throws Exception {
        AppointmentSlot slotToUpdate = repository.findById(slot.getId()).get();

        slotToUpdate.setStatus(slot.getStatus());
        slotToUpdate.setDonor(slot.getDonor());
        repository.save(slotToUpdate);
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AppointmentSlot cancelSlot(AppointmentSlot slot) {

        AppointmentSlot slotToUpdate = repository.findById(slot.getId()).get();
        if(slotToUpdate.getVersion() == 2)
            return null;

            if(LocalDateTime.now().plusDays(1).isBefore(slot.getStartTime())){
                slotToUpdate.setStatus(AppointmentStatus.WAITING);
                slotToUpdate.setDonor(null);
                repository.save(slotToUpdate);
            } else{
                throw new UnsupportedOperationException();
            }
            return slotToUpdate;
    }

    @Override
    public AppointmentSlot findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<AppointmentSlot> getAll() {
        return repository.findAll();
    }

    @Override
    public List<AppointmentSlot> getAllByBankId(Long id) {
        return repository.getAllbyBankId(id);
    }

    @Override
    public Optional<AppointmentSlot> getById(Long id) {
        return repository.findById(id);
    }

}
