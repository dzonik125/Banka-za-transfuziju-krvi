package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.AppointmentSlot;

import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot saveSlot(AppointmentSlot slot);

    AppointmentSlot cancelSlot(AppointmentSlot slot);

    AppointmentSlot findById(Long id);

    List<AppointmentSlot> getAll();
}
