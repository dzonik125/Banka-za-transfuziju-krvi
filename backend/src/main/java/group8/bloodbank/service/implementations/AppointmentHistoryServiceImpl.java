package group8.bloodbank.service.implementations;

import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.repository.AppointmentHistoryRepository;
import group8.bloodbank.service.interfaces.AppointmentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppointmentHistoryServiceImpl implements AppointmentHistoryService {

    private final AppointmentHistoryRepository appointmentHistoryRepository;

    @Autowired
    public AppointmentHistoryServiceImpl(AppointmentHistoryRepository appointmentHistoryRepository) {
        this.appointmentHistoryRepository = appointmentHistoryRepository;
    }

    @Override
    public List<AppointmentHistory> getAll() {
        return appointmentHistoryRepository.findAll();
    }

    @Override
    public Optional<AppointmentHistory> findById(Long id) {
        return appointmentHistoryRepository.findById(id);
    }

    @Override
    public void save(AppointmentHistory appointmentHistory) {
        appointmentHistoryRepository.save(appointmentHistory);
    }
}
