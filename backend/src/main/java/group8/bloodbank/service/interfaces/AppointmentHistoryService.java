package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.AppointmentHistory;

import java.util.List;
import java.util.Optional;

public interface AppointmentHistoryService {

    List<AppointmentHistory> getAll();

    Optional<AppointmentHistory> findById(Long id);

    void save(AppointmentHistory appointmentHistory);
}
